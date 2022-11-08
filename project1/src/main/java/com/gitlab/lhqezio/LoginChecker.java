package com.gitlab.lhqezio;

import java.io.IOException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.gitlab.lhqezio.util.CSV_Util;

class UserAuthData {

    String hash;
    String salt;

    public UserAuthData(String hash_, String salt_) {
        this.hash = hash_;
        this.salt = salt_;
    }
}

public class LoginChecker {

    HashMap<String, UserAuthData> byUsername = new HashMap<String, UserAuthData>();

    public LoginChecker(Path csvPath) {
        try {
            String[][] allRowsArr = CSV_Util.parseCSV(CSV_Util.readBytesAdd2Newline(csvPath));
            for (int i = 0; i < allRowsArr.length; i++) {
                String[] rowArr = allRowsArr[i];
                this.byUsername.put(rowArr[0], new UserAuthData(rowArr[1], rowArr[2]));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int check(String username_, char[] password) {
        UserAuthData userAuthData_ = this.byUsername.get(username_);
        if (userAuthData_==null) {
            return 1;
        }

        Base64.Decoder dec = Base64.getDecoder();
        KeySpec spec = new PBEKeySpec(password, dec.decode(userAuthData_.salt), 65536, 512);
        SecretKeyFactory f;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            return (enc.encodeToString(hash).equals(userAuthData_.hash)) ? 0 : 2;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
