package postclip.user.service;

import postclip.user.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class AuthenticationService{

    @PersistenceContext
    EntityManager entityManager;

    public User getUserByEmail( String email ){
        List<User> user = entityManager.createNamedQuery( User.FIND_BY_EMAIL, User.class )
                      .setParameter( "email", email ).getResultList( );
        if( user.size( ) == 0 || user.size( ) > 1 ) return null;
        else return user.get( 0 );
    }

}
