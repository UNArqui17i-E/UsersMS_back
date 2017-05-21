package postclip.user.resource;

import postclip.user.model.User;
import postclip.user.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;


@Path( "/users" )
public class UserResource{

    @Context
    UriInfo uriInfo;

    @EJB
    UserService userService;

    @GET
    public List<User> getAllUsers( @QueryParam( "first" ) int first,
      @QueryParam( "maxResult" ) int maxResult ){
        System.out.println( "    Consult all users" );
        System.out.println( "    GET ALL" );
        return userService.getAllUsers( first, maxResult );
    }

    @GET
    @Path( "{id}" )
    public User getUserById( @PathParam( "id" ) long id ){
        System.out.println( "    Consult users by ID" );
        System.out.println( "    GET " + ((Long) id).toString( ) );
        return userService.getUserById( id );
    }

    @POST
    public Response createUser( User user ){
        System.out.println( "    Create user" );
        System.out.println( "    POST CREATE " + user.getEmail( ) );
        userService.createUser( user );
        return Response.status( Response.Status.CREATED ).build( );
    }

    @PUT
    @Path( "{id}" )
    public Response updateUser( @PathParam( "id" ) long id, User user ){
        System.out.println( "    Uptade user" );
        System.out.println( "    PUT " + ((Long) id).toString( ) );
        userService.updateUser( id, user );
        return Response.status( Response.Status.NO_CONTENT ).build( );
    }

    @PUT
    @Path( "{id}" )
    public Response updatePassword( @PathParam( "id" ) long id, String password ){
        System.out.println( "    Uptade password" );
        System.out.println( "    PUT " + ((Long) id).toString( ) );
        userService.updatePassword( id, password );
        return Response.status( Response.Status.NO_CONTENT ).build( );
    }

    @DELETE
    @Path( "{id}" )
    public Response deleteUser( @PathParam( "id" ) long id ){
        System.out.println( "    Delete User" );
        System.out.println( "    DELETE " + ((Long) id).toString( ) );
        userService.deleteUser( id );
        return Response.status( Response.Status.OK ).build( );
    }

}
