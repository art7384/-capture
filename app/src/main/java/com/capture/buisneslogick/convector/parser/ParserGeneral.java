package com.capture.buisneslogick.convector.parser;

import android.util.Log;

import com.capture.model.GeneralModel;
import com.capture.object.common.BaseObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 12.01.16.
 */
public class ParserGeneral {
    private static final String LOG_TAG = "ParserGeneral";

    static public GeneralModel pars(JSONObject jsonObject) throws JSONException {
        /*{
        "id":15,
                "name":"qwecxz",
                "role":"user"
    }*/
        GeneralModel model = new GeneralModel();
        if (!jsonObject.isNull(GeneralModel.JsonKey.ID.toString())) {
            model.idObject = jsonObject.getLong(GeneralModel.JsonKey.ID.toString());
        } else {
            Log.w(LOG_TAG, "No value for " + GeneralModel.JsonKey.ID.toString());
        }
        if (!jsonObject.isNull(GeneralModel.JsonKey.NAME.toString())) {
            model.nameObject = jsonObject.getString(GeneralModel.JsonKey.NAME.toString());
        } else {
            Log.w(LOG_TAG, "No value for " + GeneralModel.JsonKey.NAME.toString());
        }
        if (!jsonObject.isNull(GeneralModel.JsonKey.ROLE.toString())) {
            String role = jsonObject.getString(GeneralModel.JsonKey.ROLE.toString());
            model.role = BaseObject.Role.get(role);
        } else {
            Log.w(LOG_TAG, "No value for " + GeneralModel.JsonKey.ROLE.toString());
        }
        return model;
    }
}
