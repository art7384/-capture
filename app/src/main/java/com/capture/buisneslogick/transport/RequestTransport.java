package com.capture.buisneslogick.transport;

import com.capture.buisneslogick.modul.RequestModul;
import com.capture.object.request.RequestObject;
import com.capture.buisneslogick.transport.helper.OnCompliteTransportListner;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 09.01.16.
 */
public class RequestTransport {

    static public void request(
            RequestObject requestObject,
            OnCompliteTransportListner listern,
            OnErrorTransportListner errorListner) throws JSONException {

        JSONObject jsonObject = requestObject.toJsonObject();
        RequestModul modul = requestObject.getRequestModul();

        Transport transport = new Transport(modul.getIdRequest());
        transport.setOnErrorTransportListner(errorListner);
        transport.setOnCompliteListern(listern);
        transport.send(jsonObject);
    }
}
