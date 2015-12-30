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
        if (model.idObject != -1) jsonObject.put(JsonKey.ID.toString(), model.idObject);
        if (model.nameObject != null) jsonObject.put(JsonKey.NAME.toString(), model.nameObject);
        if (model.role != null) jsonObject.put(JsonKey.ROLE.toString(), model.role);
        return jsonObject;
    }

    private enum JsonKey {
        ID("id"),
        ROLE("role"),
        NAME("name");

        String key;

        JsonKey(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return key;
        }

    }

}
