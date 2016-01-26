package com.capture.object.request;

import com.capture.buisneslogick.convector.parser.ParserRequest;
import com.capture.model.BaseModel;
import com.capture.model.RequestModel;
import com.capture.object.common.BaseObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 09.01.16.
 */
public class RequestObject extends BaseObject {

    private RequestModel requestModel = null;

    public RequestObject(){

    }

    public RequestObject(JSONObject jsonObject){
        try {
            JSONObject jsRequest = jsonObject.getJSONObject(requestModel.getModelType().toString());
            requestModel = ParserRequest.pars(jsRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public RequestModel getRequestModel() {
        return requestModel;
    }

    public void setRequestModel(RequestModel requestModel) {
        this.requestModel = requestModel;
    }

    @Override
    public BaseModel[] getModels() {
        return new BaseModel[]{requestModel};
    }
}
