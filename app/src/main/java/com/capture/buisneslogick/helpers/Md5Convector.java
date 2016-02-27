package com.capture.buisneslogick.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Кармишин on 27.02.2016.
 */
public class Md5Convector {
    public static final String md5(final String s) throws NoSuchAlgorithmException {
        final String MD5 = "MD5";
        // Create MD5 Hash
        MessageDigest digest = java.security.MessageDigest
                .getInstance(MD5);
        digest.update(s.getBytes());
        byte messageDigest[] = digest.digest();

        // Create Hex String
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
