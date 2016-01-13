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

        ReturnObject returnObject = new ReturnObject();

        JSONObject jsRequest = null;
        try {
            jsRequest = jsObj.getJSONObject(returnObject.getReturnModul().getJsonKey());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(jsRequest == null) return null;


        ReturnModel model = ParserReturn.pars(jsRequest);
        returnObject.getReturnModul().setReturnModul(model);

        return returnObject;
    }
}
