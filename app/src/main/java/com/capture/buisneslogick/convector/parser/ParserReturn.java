package com.capture.buisneslogick.convector.parser;

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
        model.idRequest = jsonObject.getLong(ReturnModel.JsonKey.ID.toString());
        try {
            model.text = jsonObject.getString(ReturnModel.JsonKey.TEXT.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            model.status = jsonObject.getInt(ReturnModel.JsonKey.STATUS.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return model;
    }
}
