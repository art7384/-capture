package com.capture.object;

import com.capture.buisneslogick.convector.parser.ParserGeneral;
import com.capture.buisneslogick.convector.parser.ParserReturn;
import com.capture.buisneslogick.convector.parser.ParserUser;
import com.capture.model.BaseModel;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;
import com.capture.object.common.BaseObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 07.01.16.
 */
public class UserObject extends BaseObject {

    private GeneralModel generalModel = new GeneralModel();
    private UserModel userModel = new UserModel();

    public UserObject(){

    }

    public UserObject(JSONObject jsObj){
        try {
            JSONObject jsModel = jsObj.getJSONObject(generalModel.getModelType().toString());
            generalModel = ParserGeneral.pars(jsModel);
        } catch (JSONException e) {
            e.printStackTrace();
            generalModel = null;
        }

        try {
            JSONObject jsModel = jsObj.getJSONObject(userModel.getModelType().toString());
            userModel = ParserUser.pars(jsModel);
        } catch (JSONException e) {
            e.printStackTrace();
            userModel = null;
        }

    }

    public GeneralModel getGeneralModel(){
        return generalModel;
    }

    public void setGeneralModel(GeneralModel generalModel){
        this.generalModel = generalModel;
    }

    public UserModel getUserModel(){
        return userModel;
    }

    public void setUserModel(UserModel userModel){
        this.userModel = userModel;
    }

    @Override
    public BaseModel[] getModels() {
        return new BaseModel[]{generalModel, userModel};
    }
}
