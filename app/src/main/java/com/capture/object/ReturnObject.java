package com.capture.object;

import com.capture.buisneslogick.convector.parser.ParserReturn;
import com.capture.model.BaseModel;
import com.capture.model.ReturnModel;
import com.capture.object.common.BaseObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Кармишин on 13.01.2016.
 */
public class ReturnObject extends BaseObject {

    private ReturnModel returnModel = new ReturnModel();

    public ReturnObject(){

    }

    public ReturnObject(JSONObject jsObj){
        try {
            JSONObject jsReturn = jsObj.getJSONObject(returnModel.getModelType().toString());
            returnModel = ParserReturn.pars(jsReturn);
        } catch (JSONException e) {
            e.printStackTrace();
            returnModel = null;
        }
    }

    public ReturnModel getReturnModel(){
        return returnModel;
    }

    public void setReturnModel(ReturnModel returnModel){
        this.returnModel = returnModel;
    }

    @Override
    public BaseModel[] getModels() {
        return new BaseModel[]{returnModel};
    }
}
