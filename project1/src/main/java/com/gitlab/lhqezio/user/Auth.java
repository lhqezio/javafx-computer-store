package com.gitlab.lhqezio.user;

import java.io.Console;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.gitlab.lhqezio.util.CsvLoader;
import com.gitlab.lhqezio.util.DataLoader;

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

public class Auth {

    private HashMap<String, UserData> byUsername = new HashMap<String, UserData>();

    private String savedPrivilegeLevel;
    private String savedUsername;

    public Auth() {
        DataLoader dataLoader = new CsvLoader();
        String[][] allRowsArr = dataLoader.getData("users.csv");
        for (int i = 0; i < allRowsArr.length; i++) {
            String[] rowArr = allRowsArr[i];
            this.byUsername.put(rowArr[0], new UserData(rowArr[1], rowArr[2], rowArr[3]));
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

    public void login() {
        Console myConsole = System.console();
        String username;
        while (true) {
            System.out.println("Enter your username:");
            username = myConsole.readLine();
            System.out.println("Enter your password:");
            char[] password_ = myConsole.readPassword();
            int retValue = this.check(username, password_);
            //do NOT tell users that their username is incorrect, you can find users that way, usually there's a "forgot username" button, send email
            if (retValue != 0) {
                System.out.println("incorrect credentials, try again");
                continue;
            }
            break;
        }
    }
}
