package com.capture.buisneslogick.persisten;

import com.capture.buisneslogick.modul.GeneralModul;
import com.capture.buisneslogick.modul.UserModul;
import com.capture.buisneslogick.object.UserObject;

/**
 * Created by artem on 07.01.16.
 */
public class UserProfile {

    static private UserProfile instance = null;

    private UserProfile() {

    }

    public static UserProfile getInstance() {
        if (instance == null) {
            instance = new UserProfile();
        }
        return instance;
    }

    public void exit() {

    }

    public void save(UserObject user) {

        UserModul userModul = user.getUserModul();
        GeneralModul generalModul = user.getGeneralModul();

        //generalModul.getIdObject()
        //generalModul.getNameObject()
        //generalModul.getRole()

        //userModul.getEmail()
        //userModul.getTocken()


    }

    public UserObject get() {
        UserObject user = new UserObject();
        UserModul userModul = user.getUserModul();
        GeneralModul generalModul = user.getGeneralModul();

        return user;
    }


}
