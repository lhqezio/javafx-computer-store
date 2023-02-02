package com.gitlab.lhqezio.user;

/**
 * User class that stores the user's name and privilege level.
 * Possible to add some extra functionality to this class in the future.
 * 4 levels of privilege: 0 = guest, 1 = user, 2 = vip user, 3 = admin
 * @author Hoang
 */
 
public abstract class User {
    String username;

    public String getUsername() {
        return username;
    }

    public String getWelcomeMessage() {
        throw new UnsupportedOperationException();
    }

    ;

}
