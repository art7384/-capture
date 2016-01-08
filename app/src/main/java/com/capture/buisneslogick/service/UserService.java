package com.capture.buisneslogick.service;

import com.capture.buisneslogick.object.RegistractionObject;
import com.capture.buisneslogick.object.UserObject;
import com.capture.buisneslogick.operation.user.UserSaveOperation;
import com.capture.buisneslogick.operation.user.registration.CreateRegistractionObjectOperation;
import com.capture.buisneslogick.operation.user.registration.RegistrationOperation;
import com.capture.buisneslogick.service.helpers.OnCompliteListern;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;
import com.capture.model.UserModel;

import org.json.JSONException;

import java.security.NoSuchAlgorithmException;

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

    public void registration(
            UserModel userModel,
            String nick,
            final OnCompliteListern listern,
            OnErrorTransportListner errorListner) throws NoSuchAlgorithmException, JSONException {

        RegistractionObject registractionObject = CreateRegistractionObjectOperation.create(userModel, nick);
        RegistrationOperation.registration(registractionObject, new RegistrationOperation.OnCompliteUserListner() {
            @Override
            public void onComplite(UserObject user) {
                UserSaveOperation.save(user);
                if (listern != null) {
                    listern.onComplite();
                }
            }
        }, errorListner);
    }

}
