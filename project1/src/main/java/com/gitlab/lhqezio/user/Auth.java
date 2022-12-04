package com.gitlab.lhqezio.user;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.gitlab.lhqezio.util.DataLoader;

public class Auth {

    private HashMap<String, UserData> byUsername = new HashMap<String, UserData>();

    private String savedPrivilegeLevel;
    private String savedUsername;

    public Auth(DataLoader dataLoader) {

        List<UserData> userDataList = dataLoader.getUsersData();
        for (UserData userData_ : userDataList) {
            this.byUsername.put(userData_.getUsername(), userData_);
        }
    }
    /**
     * @return 0 if login successful, 2 if password incorrect, 1 if username not found
     */
    public int check(String username_, char[] password) {
        UserData userData_ = this.byUsername.get(username_);
        if (userData_ == null) {
            return 1;
        }

        Base64.Decoder dec = Base64.getDecoder();
        KeySpec spec = new PBEKeySpec(password, dec.decode(userData_.getSalt()), 65536, 512);
        SecretKeyFactory f;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            if (enc.encodeToString(hash).equals(userData_.getHash())) {
                this.savedPrivilegeLevel = userData_.getPrivilegeLevel();
                this.savedUsername = username_;
                return 0;
            } else {
                return 2;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public User getUser() {
        switch (this.savedPrivilegeLevel) {
            case "3":
                return new AdminUser(this.savedUsername);
            case "1":
                return new NormalUser(this.savedUsername);
        }
        return null;
    }

}
