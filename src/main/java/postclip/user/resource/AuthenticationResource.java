package postclip.user.resource;

import postclip.user.model.User;
import postclip.user.model.Authentication;
import postclip.user.service.AuthenticationService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;


@Path( "/authentication" )
public class AuthenticationResource{

    @Context
    UriInfo uriInfo;
    @EJB
    AuthenticationService authenticationService;

    @POST
    public Authentication loginUser( User user ){
        System.out.println( "    Authentication for user" );
        System.out.println( "    POST " + user.getEmail( ) );
        return authenticationService.login( user );
    }

    @DELETE
    @Path("{token}")
    public Response logoutUser( @PathParam( "token" ) String token ){
        System.out.println( "    Remove Session" );
        System.out.println( "    DELETE " + token );
        authenticationService.logoutUser( token );
        return Response.status( Response.Status.OK ).build( );

    }
}
