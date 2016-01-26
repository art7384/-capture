package com.capture.buisneslogick.convector;

import com.capture.model.BaseModel;
import com.capture.model.GeneralModel;
import com.capture.model.RequestModel;
import com.capture.model.ReturnModel;
import com.capture.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 26.01.16.
 */
public class ModelConvector {

    static public JSONObject convectToJson(BaseModel model) throws JSONException {
        JSONObject jsModel = null;
        if (model.getModelType() == BaseModel.ModelType.CLIENT) {
            jsModel = null;
        } else if (model.getModelType() == BaseModel.ModelType.GENERAL) {
            jsModel = GeneralConvector.convectToJson((GeneralModel) model);
        } else if (model.getModelType() == BaseModel.ModelType.REQUEST) {
            jsModel = RequestConvector.convectToJson((RequestModel) model);
        } else if (model.getModelType() == BaseModel.ModelType.RETURN) {
            jsModel = ReturnConvector.convectToJson((ReturnModel) model);
        } else if (model.getModelType() == BaseModel.ModelType.USER) {
            jsModel = UserConvector.convectToJson((UserModel) model);
        }
        return jsModel;
    }


}
