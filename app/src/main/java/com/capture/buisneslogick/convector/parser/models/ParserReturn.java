package com.capture.buisneslogick.convector.parser.models;

import com.capture.model.RequestModel;
import com.capture.model.ReturnModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 07.01.16.
 */
public class ParserReturn {
    static public ReturnModel pars(JSONObject jsonObject) throws JSONException {
        //{"text":"Created","id":1452160944158,"status":201}
        ReturnModel model = new ReturnModel();
        model.idRequest = jsonObject.getLong("id");
        try {
            model.text = jsonObject.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            model.status = jsonObject.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return model;
    }
}
