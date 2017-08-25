package com.dannyzhou.base.utils;


import org.junit.Test;

public class TestEncryptUtils {

    @Test
    public void testEncryptDecrypt() throws Exception {

        String a = EncryptUtils.simpleEncrypt("danny");
        System.out.println(a);
        a = EncryptUtils.simpleEncrypt(a);
        System.out.println(a);
        a = EncryptUtils.simpleDecrypt(a);
        System.out.println(a);
        a = EncryptUtils.simpleDecrypt(a);
        System.out.println(a);
    }

}
