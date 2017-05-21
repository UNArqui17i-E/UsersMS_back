package postclip.user.resource;

import postclip.user.model.Authentication;
import postclip.user.service.VerificationService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;


@Path( "/verification" )
public class VerificationResource{

    @EJB
    VerificationService verificationService;

    @POST
    public String verify( Authentication authentication ){
        System.out.println( "    Verify the token" );
        System.out.println( "    POST " + authentication.getToken( ) );
        String ret = "{\"valido\":\"";
        if( verificationService.verify( authentication.getToken( ) ) )
            ret += "true\"}";
        else
            ret += "false\"}";
        return ret;
    }
}
