package postclip.user.service;

import postclip.user.model.User;
import postclip.user.model.Authentication;
import postclip.user.service.UserService;
import postclip.user.service.TokenService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AuthenticationService{

    @PersistenceContext
    EntityManager entityManager;

    @EJB
    UserService userService;

    private String createToken( User user ){
        Authentication authentication = new Authentication( );
        try{
            authentication.setToken( TokenService.createJWT( Long.toString( user.getId( ) ),
               user.getEmail( ), 1200000L ) );
            authentication.setId( user.getId( ) );
            entityManager.persist( authentication );
            return authentication.getToken( );
        }catch( Exception e ){
            return e.toString( );
        }

    }

    public String login( User user ){
        User foundUser = userService.getUserByEmail( user.getEmail( ) );
        if( foundUser == null) return "Usuario invalido";
        else if( HashService.hash( user.getPassword( ) ).equals( foundUser.getPassword( ) ) ){
            return createToken( foundUser );
        }else{
            return null;
        }
    }

    public void logoutUser( String token ){
        Authentication au = entityManager.find( Authentication.class, token );
        entityManager.remove( au );
    }
}
