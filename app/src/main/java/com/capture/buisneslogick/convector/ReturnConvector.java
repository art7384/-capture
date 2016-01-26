package com.capture.buisneslogick.convector;

import com.capture.model.ReturnModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 26.01.16.
 */
public class ReturnConvector {
    static public JSONObject convectToJson(ReturnModel model) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (model.status != -1) jsonObject.put(ReturnModel.JsonKey.STATUS.toString(), model.status);
        if (model.idRequest != -1) jsonObject.put(ReturnModel.JsonKey.ID.toString(), model.idRequest);
        if (model.text != null) jsonObject.put(ReturnModel.JsonKey.TEXT.toString(), model.text);
        return jsonObject;
    }


}
