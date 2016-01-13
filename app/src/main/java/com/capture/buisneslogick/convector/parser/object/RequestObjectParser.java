package com.capture.buisneslogick.convector.parser.object;

import com.capture.buisneslogick.convector.parser.models.ParserRequest;
import com.capture.buisneslogick.convector.parser.models.ParserReturn;
import com.capture.model.RequestModel;
import com.capture.model.ReturnModel;
import com.capture.object.ReturnObject;
import com.capture.object.request.RequestObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 13.01.16.
 */
public class RequestObjectParser {
    static public RequestObject pars(JSONObject jsObj) throws JSONException {

        RequestObject requestObject = new RequestObject();

        JSONObject jsRequest = null;
        try {
            jsRequest = jsObj.getJSONObject(requestObject.getRequestModul().getJsonKey());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(jsRequest == null) return null;

        RequestModel model = ParserRequest.pars(jsRequest);
        requestObject.getRequestModul().setRequestModel(model);

        return requestObject;
    }
}
