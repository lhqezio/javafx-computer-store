package com.gitlab.lhqezio.user;
/**
 * AdminUser class.
 * @author Fu Pei
 */
public class AdminUser extends User {

    public AdminUser(String username_) {
        this.username = username_;
    }

    public String getWelcomeMessage() {
        return "Welcome Admin.";
    }

}
