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
    TokenService tokenService;

    @POST
    public String loginUser( User user ){
        User foundUser = authenticationService.getUserByEmail( user.getEmail( ) );
        if( foundUser == null) return null;
        if( user.getPassword( ).equals( foundUser.getPassword( ) ) ){
            return tokenService.createJWT( Long.toString( foundUser.getId( ) ),
               foundUser.getName( ), 1200000L );
        }else{
            return null;
        }
    }
}
