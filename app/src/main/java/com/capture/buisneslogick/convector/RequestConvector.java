package com.capture.buisneslogick.convector;

import com.capture.model.RequestModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 30.12.15.
 */
public class RequestConvector {

    static public JSONObject convectToJson(RequestModel model) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (model.command != null) jsonObject.put(RequestModel.JsonKey.COMMAND.toString(), model.command.toString());
        if (model.idRequest != -1) jsonObject.put(RequestModel.JsonKey.ID.toString(), model.idRequest);
        return jsonObject;
    }
}
