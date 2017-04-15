package postclip.user.service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;


public final class TokenService{

    private static final String apiKey = "74207281";
    //Sample method to construct a JWT
    public static String createJWT( String id, String subject, long ttlMillis ){

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis( );
        Date now = new Date( nowMillis );

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary( apiKey );
        Key signingKey = new SecretKeySpec( apiKeySecretBytes, signatureAlgorithm.getJcaName( ) );

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder( ).setId( id )
                                    .setIssuedAt( now )
                                    .setSubject( subject )
                                    .signWith( signatureAlgorithm, signingKey );

        //if it has been specified, let's add the expiration
        if( ttlMillis >= 0 ){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date( expMillis );
            builder.setExpiration( exp );
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact( );
    }
}
