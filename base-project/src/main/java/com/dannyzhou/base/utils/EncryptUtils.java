package com.dannyzhou.base.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

    public static String encryptByMD5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return Base64Utils.encodeToString(md5.digest(password.getBytes()));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public static String simpleEncrypt(String origin) {
        byte[] bytes = origin.getBytes();

        bytes = reverseByte(bytes);
        return Base64.encodeBase64String(bytes);
    }

    public static String encrypt(String origin) {
        String internal = simpleEncrypt(origin);
        return simpleEncrypt(internal);
    }

    public static String simpleDecrypt(String origin) {

        byte[] bytes = Base64.decodeBase64(origin);
        bytes = reverseByte(bytes);
        return new String(bytes);
    }

    private static byte[] reverseByte(byte[] bytes) {

        for (int i = 0; i < bytes.length; i++) {
            byte originByte = bytes[i];
            if (originByte >= 0) {
                bytes[i] = (byte) (originByte - 128);
            } else {
                bytes[i] = (byte) (originByte + 128);
            }
        }

        return bytes;
    }
}
