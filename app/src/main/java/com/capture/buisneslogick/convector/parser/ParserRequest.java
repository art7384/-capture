package com.capture.buisneslogick.convector.parser;

import com.capture.constant.RequestCommand;
import com.capture.model.RequestModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 13.01.16.
 */
public class ParserRequest {

    static public RequestModel pars(JSONObject jsonObject) throws JSONException {

        RequestModel model = new RequestModel();
        model.idRequest = jsonObject.getLong(RequestModel.JsonKey.ID.toString());
        model.command = RequestCommand.get(jsonObject.getString(RequestModel.JsonKey.COMMAND.toString()));

        return model;
    }

}
