package com.capture.buisneslogick.convector;

import com.capture.model.RequestModel;
import com.capture.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 06.01.16.
 */
public class UserConvector {
    static public JSONObject convectToJson(UserModel model) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (model.email != null) jsonObject.put(JsonKey.EMAIL.toString(), model.email);
        if (model.password != null) jsonObject.put(JsonKey.PASSWORD.toString(), model.password);
        if (model.tocken != null) jsonObject.put(JsonKey.TOCKEN.toString(), model.tocken);
        return jsonObject;
    }

    private enum JsonKey {
        EMAIL("email"),
        TOCKEN("tocken"),
        PASSWORD("password");

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
