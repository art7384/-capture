package com.capture.buisneslogick.persisten;

import android.content.Context;
import android.content.SharedPreferences;

import com.capture.AppSoket;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;
import com.capture.object.UserObject;
import com.capture.buisneslogick.persisten.Helpers.Keys;
import com.capture.object.common.BaseObject;

/**
 * Created by artem on 07.01.16.
 */

public class UserProfile {

    static private UserProfile instance = null;
    private SharedPreferences sPref;
    SharedPreferences.Editor ed;

    private UserProfile() {
        Context context = AppSoket.getInstance().getBaseContext();
        sPref = context.getSharedPreferences("capture", context.MODE_PRIVATE);
        ed = sPref.edit();
    }

    public static UserProfile getInstance() {
        if (instance == null) {
            instance = new UserProfile();
        }
        return instance;
    }

    public void exit() {
        ed.clear();
        ed.commit();
    }

    public void save(UserObject user) {

        UserModel userModel = user.getUserModel();
        GeneralModel generalModel = user.getGeneralModel();
        putId(generalModel.idObject);
        putName(generalModel.nameObject);
        putToken(userModel.token);
        putEmail(userModel.email);
    }

    public UserObject get() {

        UserObject user = new UserObject();
        UserModel userModel = new UserModel();
        GeneralModel generalModel = new GeneralModel();

        userModel.email = getEmail();
        userModel.token = getToken();
        generalModel.idObject = getId();
        generalModel.nameObject = getName();
        generalModel.role = BaseObject.Role.USER;

        user.setGeneralModel(generalModel);
        user.setUserModel(userModel);

        return user;
    }

    private void putId(long id) {
        synchronized (Keys.ID) {
            ed.putLong(Keys.ID, id);
            ed.commit();
        }
    }

    private long getId() {
        long id;
        synchronized (Keys.ID) {
            id = sPref.getLong(Keys.ID, -1);
        }
        return id;
    }

    private void putName(String name) {
        synchronized (Keys.NICKNAME) {
            ed.putString(Keys.NICKNAME, name);
            ed.commit();
        }
    }

    private String getName() {
        String name;
        synchronized (Keys.NICKNAME) {
            name = sPref.getString(Keys.NICKNAME, null);
        }
        return name;
    }

    private String getToken() {
        String token;
        synchronized (Keys.TOKEN) {
            token = sPref.getString(Keys.TOKEN, null);
        }
        return token;
    }

    private void putToken(String token) {
        synchronized (Keys.TOKEN) {
            ed.putString(Keys.TOKEN, token);
            ed.commit();
        }
    }

    private String getEmail() {
        String email;
        synchronized (Keys.EMAIL) {
            email = sPref.getString(Keys.EMAIL, null);
        }
        return email;
    }

    private void putEmail(String email) {
        synchronized (Keys.EMAIL) {
            ed.putString(Keys.EMAIL, email);
            ed.commit();
        }
    }

}
