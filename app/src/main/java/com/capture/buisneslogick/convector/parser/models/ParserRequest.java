package com.capture.buisneslogick.convector.parser.models;

import com.capture.model.RequestModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 13.01.16.
 */
public class ParserRequest {
    static public RequestModel pars(JSONObject jsonObject) throws JSONException {

        RequestModel model = new RequestModel();
        model.idRequest = jsonObject.getLong("id");
        model.command = jsonObject.getLong("command");

        return model;
    }
}
