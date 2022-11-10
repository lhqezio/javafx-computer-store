package com.gitlab.lhqezio.user;

public class AdminUser extends User {

    public AdminUser(String username_) {
        this.username = username_;
    }

    public String getWelcomeMessage() {
        return "Welcome Admin.";
    }

}
