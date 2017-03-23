package postclip.user.model;

import javax.persistence.*;


@Entity
@Table( name = "user" )
@NamedQueries( { @NamedQuery( name = User.FIND_ALL, query = "SELECT u FROM User u" ),
                 @NamedQuery( name = User.FIND_BY_EMAIL,
                 query = "SELECT u FROM User u WHERE u.email = :email" ) } )
public class User{

    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_BY_EMAIL = "User.findByEmail";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;
    private String name;
    private String nick;
    private String email;
    private String password;

    public long getId( ){
        return id;
    }

    public void setId( long id ){
        this.id = id;
    }

    public String getName( ){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }

    public String getNick( ){
        return nick;
    }

    public void setNick( String nick ){
        this.nick = nick;
    }

    public String getEmail( ){
        return email;
    }

    public void setEmail( String email ){
        this.email = email;
    }

    public String getPassword( ){
        return password;
    }

    public void setPassword( String password ){
        this.password = password;
    }

}
