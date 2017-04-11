package postclip.user.service;

import postclip.user.model.User;

import java.security.MessageDigest;

public final class HashService{

    private static MessageDigest sha256;

    public static String hash( String password ){
        try{
            sha256 = MessageDigest.getInstance( "SHA-256" );
            sha256.reset( );
            sha256.update( password.getBytes( "UTF-8" ) );
            byte[] digest = sha256.digest( );
            StringBuffer sb = new StringBuffer( );
            for( int i = 0; i < digest.length; i++ ){
                sb.append( String.format( "%02x", digest[i] ) );
            }
            return sb.toString( );
        }catch( Exception e ){
            return e.toString( );
        }
    }

}
