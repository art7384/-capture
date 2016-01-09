package com.capture.buisneslogick.operation.user;

import com.capture.buisneslogick.convector.parser.object.UserObjectParser;
import com.capture.buisneslogick.object.requestclien.UserRequestObject;
import com.capture.buisneslogick.object.requestserver.RequestServerObject;
import com.capture.buisneslogick.object.UserObject;
import com.capture.buisneslogick.transport.RequestTransport;
import com.capture.buisneslogick.transport.UserRequestTransport;
import com.capture.buisneslogick.transport.helper.OnCompliteTransportListner;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;

import org.json.JSONException;

/**
 * Created by artem on 08.01.16.
 */
public class RegistrationOperation {

    static public void registration(final UserRequestObject registractionRequestObject, final OnCompliteUserListner listern, OnErrorTransportListner errorListner) throws JSONException {
        RequestTransport.request(
                registractionRequestObject,
                new OnCompliteTransportListner() {
                    @Override
                    public void OnComplite(String s, RequestServerObject requestServerObject) {
                        UserObject user = UserObjectParser.pars(s);
                        if (listern != null) {
                            listern.onComplite(user);
                        }
                    }
                },
                errorListner);
    }

    public interface OnCompliteUserListner {
        void onComplite(UserObject user);
    }

}
