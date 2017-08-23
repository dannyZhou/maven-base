package com.dannyzhou.base.utils;

import org.springframework.util.Base64Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

    private EncryptUtils() {

    }

    public static String encryptByMD5(String password) {
        try {
            MessageDigest md5= MessageDigest.getInstance("MD5");
           return Base64Utils.encodeToString(md5.digest(password.getBytes()));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }
}
