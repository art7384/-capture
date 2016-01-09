package com.capture.buisneslogick.operation.user;

import com.capture.buisneslogick.object.requestclien.RequestObject;
import com.capture.buisneslogick.object.requestserver.RequestServerObject;
import com.capture.buisneslogick.persisten.UserProfile;
import com.capture.buisneslogick.service.helpers.OnCompliteListern;
import com.capture.buisneslogick.transport.RequestTransport;
import com.capture.buisneslogick.transport.helper.OnCompliteTransportListner;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;

import org.json.JSONException;

/**
 * Created by artem on 09.01.16.
 */
public class ExitOperation {
    static public void exit(RequestObject exitRequestObject, final OnCompliteTransportListner listern, OnErrorTransportListner errorListner) throws JSONException {

        RequestTransport.request(
                exitRequestObject,
                listern,
                errorListner);
    }
}
