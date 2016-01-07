package com.capture.buisneslogick.service;

import com.capture.AppSoket;
import com.capture.buisneslogick.modul.GeneralModul;
import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.modul.UserModul;
import com.capture.buisneslogick.object.RegistractionObject;
import com.capture.buisneslogick.object.UserObject;
import com.capture.buisneslogick.transport.RegistrationTransport;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;
import com.capture.role.Role;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by artem on 06.01.16.
 */
public class UserService {
    static private UserService instance = null;

    private UserService(){

    }

    public static UserService getInstance() {
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }

    public void registration(UserModel userModel, String nick, final OnCompliteUserListner  listern) throws NoSuchAlgorithmException, JSONException {
        RegistractionObject registractionObject = createRegistractionObject(userModel, nick);
        RegistrationTransport.registration(registractionObject, new AppSoket.OnCompliteListern(){
            @Override
            public void onComplite(String s) {
                updateUser(s, listern);
            }
        });
    }

    private void updateUser(String response, OnCompliteUserListner  listern){
        UserObject user = new UserObject();
        listern.onComplite(user);
    }

    private RegistractionObject createRegistractionObject(UserModel userModel, String nick) throws NoSuchAlgorithmException {
        RegistractionObject object = new RegistractionObject();

        RequestModul request = object.getRequestModul();
        request.setConnand("create_user");
        request.setIdRequest(new Date().getTime());

        GeneralModul general = object.getGeneralModel();
        general.setNameObject(nick);
        general.setRole(Role.USER);

        UserModul user = object.getUserModul();
        user.setEmail(userModel.email);
        user.setPassword(userModel.password);

        return object;

    }

    public interface OnCompliteUserListner{
        void onComplite(UserObject user);
    }

}
