package postclip.user.model;

import javax.persistence.*;


@Entity
@Table( name = "authentication" )
@NamedQueries( { @NamedQuery( name = Authentication.FIND_BY_ID,
                 query = "SELECT a FROM Authentication a WHERE a.id = :id" ) } )

public class Authentication{

    public static final String FIND_BY_ID = "User.findByid";

    @Id
    private String token;
    private long id;

    public Authentication( ){}

    public String getToken( ){
        return token;
    }

    public void setToken( String token ){
        this.token = token;
    }

    public long getId( ){
        return id;
    }

    public void setId( long id ){
        this.id = id;
    }
}
