package com.capture.buisneslogick.modul;

import com.capture.buisneslogick.convector.RequestConvector;
import com.capture.buisneslogick.convector.UserConvector;
import com.capture.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by artem on 06.01.16.
 */
public class UserModul implements BaseModul {

    private UserModel model = new UserModel();

    @Override
    public JSONObject toJsonObject() throws JSONException {
        return UserConvector.convectToJson(model);
    }

    @Override
    public String getJsonKey() {
        return "user";
    }

    public String getEmail() {
        return model.email;
    }

    public void setEmail(String email) {
        model.email = email;
    }

    public String getPassword() {
        return model.password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        model.password = md5(password);
    }

    public String getTocken() {
        return model.tocken;
    }

    public void setTocken(String tocken) {
        model.tocken = tocken;
    }

    /* ===== HELPERS ===== */

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
