package com.capture.buisneslogick.convector;

import com.capture.model.ClientModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 30.12.15.
 */
public class ClientConvector {

    static public JSONObject convectToJson(ClientModel model) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (model.device != null) jsonObject.put(JsonKey.DEVICE.toString(), model.device);
        if (model.versionClient != null)
            jsonObject.put(JsonKey.VERSION.toString(), model.versionClient);
        if (model.os != null) jsonObject.put(JsonKey.OS.toString(), model.os);
        return jsonObject;
    }

    private enum JsonKey {
        DEVICE("device"),
        OS("os"),
        VERSION("version");

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
