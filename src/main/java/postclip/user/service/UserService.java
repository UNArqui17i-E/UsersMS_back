package postclip.user.service;

import postclip.user.model.User;
import postclip.user.service.HashService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class UserService{

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAllUsers( int first, int maxResult ){
        return entityManager.createNamedQuery( User.FIND_ALL )
         .setFirstResult( first ).setMaxResults( maxResult ).getResultList( );
    }

    public User getUserById( long id ){
        return entityManager.find( User.class, id );
    }

    public User getUserByEmail( String email ){
        List<User> user = entityManager.createNamedQuery( User.FIND_BY_EMAIL, User.class )
                      .setParameter( "email", email ).getResultList( );
        if( user.size( ) == 0 || user.size( ) > 1 ) return null;
        else return user.get( 0 );
    }

    public void createUser( User user ){
        user.setPassword( HashService.hash( user.getPassword( ) ) );
        entityManager.persist( user );
    }

    public User updateUser( long id, User user ){
        User userToUpdate = entityManager.find( User.class, id );
        userToUpdate.setName( user.getName( ) );
        userToUpdate.setEmail( user.getEmail( ) );
        userToUpdate.setPassword( HashService.hash( user.getPassword( ) ) );
        return entityManager.merge( userToUpdate );
    }

    public User updatePassword( long id, String password ){
        User userToUpdate = entityManager.find( User.class, id );
        userToUpdate.setPassword( HashService.hash( password ) );
        return entityManager.merge( userToUpdate );
    }

    public void deleteUser( long id ){
        User user = entityManager.find( User.class, id );
        entityManager.remove( user );
    }
}
