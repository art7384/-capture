package com.capture.buisneslogick.operation;

import com.capture.AppSoket;
import com.capture.buisneslogick.transport.OnCompliteListner;
import com.capture.buisneslogick.transport.OnErrorTransportListner;
import com.capture.object.ReturnObject;
import com.capture.object.request.RegistrationRequestObject;

import org.json.JSONObject;

/**
 * Created by artem on 26.01.16.
 */
public class Registration {
    public static void registration(
            RegistrationRequestObject object,
            final OnCompliteListner listner,
            final OnErrorTransportListner errorListner) {

        AppSoket.getInstance().send(
                object.toJSONObject(),
                object.getRequestModel().idRequest,
                new AppSoket.OnCompliteListern() {
                    @Override
                    public void onComplite(JSONObject jsObj, ReturnObject returnObject) {
                        if((returnObject.getReturnModel().status / 100) == 2){
                            listner.onComplite();
                        } else {
                            errorListner.onError(returnObject);
                        }
                    }
                }
        );
    }

}
