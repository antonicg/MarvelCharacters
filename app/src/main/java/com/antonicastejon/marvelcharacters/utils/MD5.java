package com.antonicastejon.marvelcharacters.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class MD5 {

    private static final String MD5 = "MD5";

    @Inject
    public MD5() {}


    public String getMD5(String encryptText) throws Exception {
        byte messageDigest[] = getMessageDigest(encryptText);
        return generateHexString(messageDigest);
    }

    private byte[] getMessageDigest(String fromString) throws NoSuchAlgorithmException {
        MessageDigest digest = java.security.MessageDigest
                .getInstance(MD5);
        digest.update(fromString.getBytes());
        return digest.digest();
    }

    private String generateHexString(byte[] messageDigest) {
        StringBuilder hexString = new StringBuilder();
        for (byte aMessageDigest : messageDigest) {
            String h = Integer.toHexString(0xFF & aMessageDigest);
            while (h.length() < 2)
                h = "0" + h;
            hexString.append(h);
        }
        return hexString.toString();
    }

}
