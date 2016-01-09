package com.capture.buisneslogick.service;

import com.capture.buisneslogick.object.requestclien.RequestObject;
import com.capture.buisneslogick.object.requestclien.UserRequestObject;
import com.capture.buisneslogick.object.UserObject;
import com.capture.buisneslogick.object.requestserver.RequestServerObject;
import com.capture.buisneslogick.operation.common.CreateRequestOperation;
import com.capture.buisneslogick.operation.user.ExitOperation;
import com.capture.buisneslogick.operation.user.CreateUserRequestObjectOperation;
import com.capture.buisneslogick.operation.user.RegistrationOperation;
import com.capture.buisneslogick.persisten.UserProfile;
import com.capture.buisneslogick.service.helpers.OnCompliteListern;
import com.capture.buisneslogick.transport.helper.OnCompliteTransportListner;
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

        UserRequestObject registractionRequestObject = CreateUserRequestObjectOperation.createRegistration(userModel, nick);
        RegistrationOperation.registration(registractionRequestObject, new RegistrationOperation.OnCompliteUserListner() {
            @Override
            public void onComplite(UserObject user) {
                UserProfile.getInstance().save(user);
                if (listern != null) {
                    listern.onComplite();
                }
            }
        }, errorListner);
    }

    public void authorization(
            UserModel userModel,
            final OnCompliteListern listern,
            OnErrorTransportListner errorListner) throws NoSuchAlgorithmException, JSONException {

        UserRequestObject authorization = CreateUserRequestObjectOperation.createAthorization(userModel);
        RegistrationOperation.registration(authorization, new RegistrationOperation.OnCompliteUserListner() {
            @Override
            public void onComplite(UserObject user) {
                UserProfile.getInstance().save(user);
                if (listern != null) {
                    listern.onComplite();
                }
            }
        }, errorListner);
    }

    public void exit(final OnCompliteListern listern, OnErrorTransportListner errorLiistner) throws JSONException {
        RequestObject exit = CreateRequestOperation.createExit();
        ExitOperation.exit(exit, new OnCompliteTransportListner(){

            @Override
            public void OnComplite(String s, RequestServerObject requestServerObject) {
                UserProfile.getInstance().exit();
                if(listern != null){
                    listern.onComplite();
                }
            }
        }, errorLiistner);
    }

}
