package com.capture.buisneslogick.convector.parser.object;

import com.capture.buisneslogick.object.requestserver.RequestServerObject;
import com.capture.buisneslogick.convector.parser.models.ParserRequest;
import com.capture.model.RequestModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 07.01.16.
 */
public class RequestServerObjectParser {

    static public RequestServerObject pars(JSONObject jsObj) throws JSONException {

        JSONObject jsRequest = null;
        try {
            jsRequest = jsObj.getJSONObject("request");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(jsRequest == null) return null;

        RequestServerObject request = new RequestServerObject();
        RequestModel model = ParserRequest.pars(jsRequest);
        request.setRequestModel(model);

        return request;
    }
}
