package com.gitlab.lhqezio;

import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.gitlab.lhqezio.user.AdminUser;
import com.gitlab.lhqezio.user.NormalUser;
import com.gitlab.lhqezio.user.User;
import com.gitlab.lhqezio.util.CSV_Util;

class UserData { //use this instead of String[3]

    public String privilegeLevel;
    public String hash;
    public String salt;

    public UserData(String privilegeLevel_, String hash_, String salt_) {
        this.privilegeLevel = privilegeLevel_;
        this.hash = hash_;
        this.salt = salt_;
    }
}

public class LoginChecker {

    private HashMap<String, UserData> byUsername = new HashMap<String, UserData>();

    private String savedPrivilegeLevel;
    private String savedUsername;

    public LoginChecker(Path csvPath) {
        try {
            String[][] allRowsArr = CSV_Util.parseCSV(CSV_Util.readBytesAdd2Newline(csvPath));
            for (int i = 0; i < allRowsArr.length; i++) {
                String[] rowArr = allRowsArr[i];
                this.byUsername.put(rowArr[0], new UserData(rowArr[1], rowArr[2], rowArr[3]));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int check(String username_, char[] password) {
        UserData userData_ = this.byUsername.get(username_);
        if (userData_ == null) {
            return 1;
        }

        Base64.Decoder dec = Base64.getDecoder();
        KeySpec spec = new PBEKeySpec(password, dec.decode(userData_.salt), 65536, 512);
        SecretKeyFactory f;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            if (enc.encodeToString(hash).equals(userData_.hash)) {
                this.savedPrivilegeLevel = userData_.privilegeLevel;
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
