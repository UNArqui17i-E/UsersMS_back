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

    private Authentication createToken( User user ){
        System.out.println( "    Creating token..." );
        Authentication authentication = new Authentication( );
        try{
            authentication.setToken( TokenService.createJWT( Long.toString( user.getId( ) ),
               user.getEmail( ), 1200000L ) );
            authentication.setId( user.getId( ) );
            entityManager.persist( authentication );
            System.out.println( "    Token created!" );
            return authentication;
        }catch( Exception e ){
            System.out.println( "    Error in creat token" );
            return null;
        }
    }

    public Authentication getAuthentication( String token ){
        System.out.println( "    Getting Authentication" );
        try{
            return entityManager.find( Authentication.class, token );
        }catch( Exception e ){
            System.out.println( "    Finding error" );
            return null;
        }

    }

    public Authentication login( User user ){
        System.out.println( "    Validing the information for login" );
        User foundUser = userService.getUserByEmail( user.getEmail( ) );
        if( foundUser == null){
            System.out.println( "    The user email isn't found" );
            return null;
        }else if( HashService.hash( user.getPassword( ) ).equals( foundUser.getPassword( ) ) ){
            System.out.println( "    Login successfully" );
            return createToken( foundUser );
        }else{
            System.out.println( "    The user email isn't found" );
            return null;
        }
    }

    public void logoutUser( String token ){
        System.out.println( "    Removing session..." );
        try{
            Authentication au = entityManager.find( Authentication.class, token );
            entityManager.remove( au );
            System.out.println( "    Session deleted" );
        }catch( Exception e ){
            System.out.println( "    Failure" );
        }
    }
}
