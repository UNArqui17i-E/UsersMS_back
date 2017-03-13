package postclip.user.model;

import javax.persistence.*;


@Entity
@Table( name = "tokens" )
@NamedQueries( { @NamedQuery( name = Authentication.FIND_SESSION,
                 query = "SELECT a FROM Authentication a WHERE a.token = :token" ) } )
public class Authentication{

    public static final String FIND_SESSION = "Authentication.findSession";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;
    private String token;
    private long userId;

    public long getId( ){
        return id;
    }

    public void setId( long id ){
        this.id = id;
    }

    public String getToken( ){
        return token;
    }

    public void setToken( String token ){
        this.token = token;
    }

    public long getUserId( ){
        return userId;
    }

    public void setUserId( long userId ){
        this.userId = userId;
    }

}
