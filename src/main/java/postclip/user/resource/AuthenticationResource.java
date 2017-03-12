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


@Path( "/login" )
public class AuthenticationResource{

    @Context
    UriInfo uriInfo;

    @EJB
    UserService userService;

    @POST
    public String loginUser( String email, String password ){
        List<User> user = AuthenticationService.getUserByEmail( email );
        return Response.status( Response.Status.CREATED ).build( );
    }

}
