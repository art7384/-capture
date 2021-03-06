package com.capture.object;

import com.capture.model.BaseModel;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;
import com.capture.object.common.BaseObject;

/**
 * Created by artem on 07.01.16.
 */
public class UserObject extends BaseObject {

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
        return new BaseModel[]{generalModel, userModel};
    }
}
