package postclip.user.resource;

import postclip.user.model.User;
import postclip.user.model.Authentication;
import postclip.user.service.AuthenticationService;
import postclip.user.service.TokenService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;


@Path( "/login" )
public class AuthenticationResource{

    @Context
    UriInfo uriInfo;

    @EJB
    AuthenticationService authenticationService;

    @POST
    public String loginUser( User user ){
        System.out.println( user );
        User foundUser = authenticationService.getUserByEmail( user.getEmail( ) );
        if( foundUser == null) return null;
        if( user.getPassword( ).equals( foundUser.getPassword( ) ) ){
            try{
                return TokenService.createJWT( Long.toString( foundUser.getId( ) ),
                   foundUser.getName( ), 1200000L );
            }catch( Exception e ){
                return e.toString( );
            }
        }else{
            return null;
        }
    }
}
