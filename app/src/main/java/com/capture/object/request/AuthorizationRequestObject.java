package com.capture.object.request;

import com.capture.model.BaseModel;
import com.capture.model.UserModel;

/**
 * Created by artem on 26.01.16.
 */
public class AuthorizationRequestObject extends RequestObject {
    private UserModel userModel = null;

    public UserModel getUserModel(){
        return userModel;
    }

    public void setUserModel(UserModel userModel){
        this.userModel = userModel;
    }

    @Override
    public BaseModel[] getModels() {
        return new BaseModel[]{getRequestModel(), userModel};
    }
}
