package com.capture.buisneslogick.convector.parser.object;

import com.capture.model.ReturnModel;
import com.capture.object.ReturnObject;
import com.capture.buisneslogick.convector.parser.models.ParserReturn;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 07.01.16.
 */
public class ReturnObjectParser {

    static public ReturnObject pars(JSONObject jsObj) throws JSONException {

        JSONObject jsRequest = null;
        try {
            jsRequest = jsObj.getJSONObject("request");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(jsRequest == null) return null;

        ReturnObject returnObject = new ReturnObject();
        ReturnModel model = ParserReturn.pars(jsRequest);
        returnObject.getReturnModul().setReturnModul(model);

        return returnObject;
    }
}
