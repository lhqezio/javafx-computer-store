package com.gitlab.lhqezio.user;

public class NormalUser extends User {

    public NormalUser(String username_) {
        this.username = username_;
    }

    public String getWelcomeMessage() {
        return "Welcome User.";
    }

}
