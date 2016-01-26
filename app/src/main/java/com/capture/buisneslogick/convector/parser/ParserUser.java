package com.capture.buisneslogick.convector.parser;

import com.capture.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 12.01.16.
 */
public class ParserUser {
    static public UserModel pars(JSONObject jsonObject) throws JSONException {
        /*{{
                "email":"qwecfr@gf.gt",
                "token":"345a7c1da6ff89eb4da7a651528a4546"
            }*/
        UserModel model = new UserModel();
        model.email = jsonObject.getString(UserModel.JsonKey.EMAIL.toString());
        model.tocken = jsonObject.getString(UserModel.JsonKey.TOKEN.toString());
        return model;
    }
}
