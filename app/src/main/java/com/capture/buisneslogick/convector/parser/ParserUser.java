package com.capture.buisneslogick.convector.parser;

import android.util.Log;

import com.capture.model.GeneralModel;
import com.capture.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 12.01.16.
 */
public class ParserUser {
    private static final String LOG_TAG = "ParserUser";

    static public UserModel pars(JSONObject jsonObject) throws JSONException {
        /*{{
                "email":"qwecfr@gf.gt",
                "token":"345a7c1da6ff89eb4da7a651528a4546"
            }*/
        UserModel model = new UserModel();
        model.email = jsonObject.getString(UserModel.JsonKey.EMAIL.toString());
        if(!jsonObject.isNull(UserModel.JsonKey.TOKEN.toString())){
            model.token = jsonObject.getString(UserModel.JsonKey.TOKEN.toString());
        } else {
            Log.w(LOG_TAG, "No value for " + UserModel.JsonKey.TOKEN.toString());
        }
        if (!jsonObject.isNull(UserModel.JsonKey.PASSWORD.toString())) {
            model.password = jsonObject.getString(UserModel.JsonKey.PASSWORD.toString());
        } else {
            Log.w(LOG_TAG, "No value for " + UserModel.JsonKey.PASSWORD.toString());
        }
        return model;
    }
}
