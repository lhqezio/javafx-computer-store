package com.gitlab.lhqezio;
/**
 * User class that stores the user's name and privilege level.
 * Possible to add some extra functionality to this class in the future.
 * 4 levels of privilege: 0 = guest, 1 = user, 2 = vip user, 3 = admin
 *
 */
public class User {
    private String username;
    private int userType;
    public User(String username,  int userType) {
        this.username = username;
        this.userType = userType;
    }
    public User(){
        this.username = "guest";
        this.userType = 0;
    }
    public String getUsername(){
        return username;
    };
    public int getUserType(){
        return userType;
    };
}
