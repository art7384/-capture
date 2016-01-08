package com.capture.buisneslogick.operation.user.registration;

import com.capture.AppSoket;
import com.capture.buisneslogick.object.RegistractionObject;
import com.capture.buisneslogick.object.RequestServerObject;
import com.capture.buisneslogick.object.UserObject;
import com.capture.buisneslogick.transport.RegistrationTransport;
import com.capture.buisneslogick.transport.helper.OnCompliteTransportListner;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;

import org.json.JSONException;

import java.security.NoSuchAlgorithmException;

/**
 * Created by artem on 08.01.16.
 */
public class RegistrationOperation {

    static public void registration(final RegistractionObject registractionObject, final OnCompliteUserListner listern, OnErrorTransportListner errorListner) throws JSONException {
        RegistrationTransport.registration(
                registractionObject,
                new OnCompliteTransportListner() {
                    @Override
                    public void OnComplite(String s, RequestServerObject requestServerObject) {
                        UserObject user = parsUserObject(s);
                        if(listern != null){
                            listern.onComplite(user);
                        }
                    }
                },
                errorListner);
    }

    static private UserObject parsUserObject(String s) {
        return null;
    }

    public interface OnCompliteUserListner {
        void onComplite(UserObject user);
    }

}
