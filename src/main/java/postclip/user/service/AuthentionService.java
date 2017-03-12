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

    public List<User> getUserByEmail( String email ){
        return entityManager.createNamedQuery( User.FIND_BY_EMAIL, email )
                            .setParameter("email", email ).getResultList( );
    }

}
