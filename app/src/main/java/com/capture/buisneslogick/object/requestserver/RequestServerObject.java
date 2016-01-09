package com.capture.buisneslogick.object.requestserver;

import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.object.common.BaseObject;
import com.capture.model.RequestModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 07.01.16.
 */
public class RequestServerObject extends BaseObject {

    private final RequestModul requestModul = new RequestModul();

    public RequestModul getRequestModul(){
        return requestModul;
    }

    public void setRequestModel (RequestModel model){
        requestModul.setConnand(model.command);
        requestModul.setIdRequest(model.idRequest);
        requestModul.setStatus(model.status);
        requestModul.setText(model.text);
    }

    @Override
    public JSONObject toJsonObject() throws JSONException {
        return null;
    }
}
