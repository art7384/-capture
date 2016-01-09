package com.capture.buisneslogick.object.requestclien;

import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.object.common.BaseObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 09.01.16.
 */
public class RequestObject extends BaseObject {

    private final RequestModul requestModul = new RequestModul();

    @Override
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(requestModul.getJsonKey(), requestModul.toJsonObject());
        return jsonObject;
    }

    public RequestModul getRequestModul(){
        return requestModul;
    }


}
