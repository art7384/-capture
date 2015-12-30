package com.capture.buisneslogick.object;

import com.capture.buisneslogick.modul.BaseModul;
import com.capture.buisneslogick.modul.ClientModul;
import com.capture.buisneslogick.modul.GeneralModul;
import com.capture.buisneslogick.modul.RequestModul;
import com.capture.model.RequestModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 30.12.15.
 */
public class RequestClientObject extends BaseObject {

    private final RequestModul requestModul = new RequestModul();
    private final ClientModul clientModul = new ClientModul();


    public RequestModul getRequestModul(){
        return requestModul;
    }

    public ClientModul getClientModul(){
        return clientModul;
    }

    @Override
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(requestModul.getJsonKey(), requestModul.toJsonObject());
        jsonObject.put(clientModul.getJsonKey(), clientModul.toJsonObject());
        return jsonObject;
    }
}
