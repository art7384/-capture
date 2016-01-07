package com.capture.buisneslogick.transport;

import com.capture.AppSoket;
import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.object.RegistractionObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 07.01.16.
 */
public class RegistrationTransport {

    static public void registration(RegistractionObject registractionObject, AppSoket.OnCompliteListern listern) throws JSONException {
        JSONObject jsonObject = registractionObject.toJsonObject();
        RequestModul modul = registractionObject.getRequestModul();
        AppSoket.getInstance().send(jsonObject, modul.getIdRequest(), listern);
    }

}
