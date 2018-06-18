package com.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-18T16:08:49")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> country;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> phoneNr;
    public static volatile SingularAttribute<Users, String> cardId;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> username;
    public static volatile SingularAttribute<Users, Date> updatedAt;

}