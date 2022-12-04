package com.gitlab.lhqezio.user;

public class UserData {

    private String username;
    private String privilegeLevel;
    private String hash;
    private String salt;

    public UserData(String username_, String privilegeLevel_, String hash_, String salt_) {
        this.username = username_;
        this.privilegeLevel = privilegeLevel_;
        this.hash = hash_;
        this.salt = salt_;
    }

    public String getUsername() {
        return username;
    }

    public String getPrivilegeLevel() {
        return privilegeLevel;
    }

    public String getHash() {
        return hash;
    }

    public String getSalt() {
        return salt;
    }

}
