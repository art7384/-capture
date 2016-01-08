package com.capture.buisneslogick.transport;

import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.object.RegistractionObject;
import com.capture.buisneslogick.transport.helper.OnCompliteTransportListner;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 07.01.16.
 */
public class RegistrationTransport {

    static public void registration(RegistractionObject registractionObject, OnCompliteTransportListner listern, OnErrorTransportListner errorListner) throws JSONException {

        JSONObject jsonObject = registractionObject.toJsonObject();
        RequestModul modul = registractionObject.getRequestModul();

        Transport transport = new Transport(modul.getIdRequest());
        transport.setOnErrorTransportListner(errorListner);
        transport.setOnCompliteListern(listern);
        transport.send(jsonObject);
    }

}
