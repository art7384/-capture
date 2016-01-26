package com.capture.buisneslogick.convector;

import com.capture.model.GeneralModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 30.12.15.
 */
public class GeneralConvector {

    static public JSONObject convectToJson(GeneralModel model) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (model.idObject != -1) jsonObject.put(GeneralModel.JsonKey.ID.toString(), model.idObject);
        if (model.nameObject != null) jsonObject.put(GeneralModel.JsonKey.NAME.toString(), model.nameObject);
        if (model.role != null) jsonObject.put(GeneralModel.JsonKey.ROLE.toString(), model.role.toString());
        return jsonObject;
    }



}
