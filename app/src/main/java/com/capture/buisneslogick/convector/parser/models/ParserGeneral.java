package com.capture.buisneslogick.convector.parser.models;

import com.capture.constant.ObjectRole;
import com.capture.model.GeneralModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 12.01.16.
 */
public class ParserGeneral {
    static public GeneralModel pars(JSONObject jsonObject) throws JSONException {
        /*{
        "id":15,
                "name":"qwecxz",
                "role":"user"
    }*/
        GeneralModel model = new GeneralModel();
        model.idObject = jsonObject.getLong("id");
        try {
            model.nameObject = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            String role = jsonObject.getString("role");
            model.role = ObjectRole.get(role);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return model;
    }
}
