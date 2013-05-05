package enterprise.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-05-05T13:29:44")
@StaticMetamodel(Player.class)
public class Player_ { 

    public static volatile SingularAttribute<Player, Long> id;
    public static volatile SingularAttribute<Player, Boolean> playing;
    public static volatile SingularAttribute<Player, String> email;
    public static volatile SingularAttribute<Player, Boolean> waiting;
    public static volatile SingularAttribute<Player, String> login;
    public static volatile SingularAttribute<Player, String> password;

}