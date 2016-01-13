package com.capture.buisneslogick.transport;

import com.capture.buisneslogick.modul.RequestModul;
import com.capture.object.request.UserRequestObject;
import com.capture.buisneslogick.transport.helper.OnCompliteTransportListner;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 07.01.16.
 */
public class UserRequestTransport {

    static public void request(
            UserRequestObject registractionRequestObject,
            OnCompliteTransportListner listern,
            OnErrorTransportListner errorListner) throws JSONException {

        JSONObject jsonObject = registractionRequestObject.toJsonObject();
        RequestModul modul = registractionRequestObject.getRequestModul();

        Transport transport = new Transport(modul.getIdRequest());
        transport.setOnErrorTransportListner(errorListner);
        transport.setOnCompliteListern(listern);
        transport.send(jsonObject);
    }

}
