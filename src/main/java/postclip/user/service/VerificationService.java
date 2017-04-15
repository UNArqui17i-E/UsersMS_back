package postclip.user.service;

import postclip.user.model.Authentication;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class VerificationService{

    @EJB
    AuthenticationService authenticationService;

    public boolean verify( String token ){
        Authentication authentication = authenticationService.getAuthentication( token );
        if( authentication == null ){
            return false;
        }else
            return true;
    }

}
