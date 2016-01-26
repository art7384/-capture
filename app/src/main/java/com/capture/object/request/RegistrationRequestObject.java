package com.capture.object.request;

import com.capture.model.BaseModel;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;

/**
 * Created by artem on 26.01.16.
 */
public class RegistrationRequestObject extends RequestObject {

    private GeneralModel generalModel = null;
    private UserModel userModel = null;

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
        return new BaseModel[]{getRequestModel(), generalModel, userModel};
    }
}
