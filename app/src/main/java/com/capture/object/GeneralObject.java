package com.capture.object;

import android.util.Log;

import com.capture.buisneslogick.convector.parser.ParserGeneral;
import com.capture.buisneslogick.convector.parser.ParserUser;
import com.capture.model.BaseModel;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;
import com.capture.object.common.BaseObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by artem on 13.04.16.
 */
public class GeneralObject extends BaseObject {

    private static final String LOG_TAG = "UserObject";
    private GeneralModel generalModel = new GeneralModel();

    public GeneralObject(){

    }

    public GeneralObject(JSONObject jsObj){
        try {
            JSONObject jsModel = jsObj.getJSONObject(generalModel.getModelType().toString());
            generalModel = ParserGeneral.pars(jsModel);
        } catch (JSONException e) {
            //e.printStackTrace();
            Log.w(LOG_TAG, "No value for " + generalModel.getModelType().toString());
            generalModel = new GeneralModel();
        }

    }

    public GeneralModel getGeneralModel(){
        return generalModel;
    }

    public void setGeneralModel(GeneralModel generalModel){
        this.generalModel = generalModel;
    }

    @Override
    public BaseModel[] getModels() {
        return new BaseModel[]{generalModel};
    }
}
