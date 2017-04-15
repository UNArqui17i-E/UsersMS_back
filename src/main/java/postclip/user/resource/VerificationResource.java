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
    public boolean verify( Authentication authentication ){
        return verificationService.verify( authentication.getToken( ) );
    }
}
