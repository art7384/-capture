package com.capture.buisneslogick.service;

import com.capture.buisneslogick.operation.CreaterRegistrationRequestObject;
import com.capture.buisneslogick.operation.Registration;
import com.capture.buisneslogick.transport.OnCompliteListner;
import com.capture.object.request.RegistrationRequestObject;
import com.capture.object.UserObject;
import com.capture.buisneslogick.persisten.UserProfile;
import com.capture.buisneslogick.transport.OnErrorTransportListner;
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
            OnCompliteListner listern,
            OnErrorTransportListner errorListner) throws NoSuchAlgorithmException, JSONException {


        RegistrationRequestObject registractionRequestObject = CreaterRegistrationRequestObject.create(userModel, nick);
        Registration.registration(registractionRequestObject,listern, errorListner);
    }

    public UserObject getUserObject(){
        return UserProfile.getInstance().get();
    }

    public boolean isAuthorization() {
        if(getUserObject().getUserModel() == null) return false;
        String token = getUserObject().getUserModel().tocken;
        return token != null;
    }
}
