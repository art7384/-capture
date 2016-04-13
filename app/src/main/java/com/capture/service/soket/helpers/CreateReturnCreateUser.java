package com.capture.service.soket.helpers;

import com.capture.buisneslogick.operation.CreateReturnObject;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;
import com.capture.object.ReturnObject;
import com.capture.object.UserObject;
import com.capture.object.request.RequestObject;

import org.json.JSONArray;

/**
 * Created by Кармишин on 13.04.2016.
 */
public class CreateReturnCreateUser {
    static public String create(RequestObject requestObject, UserObject userObject){
        JSONArray arrJsReturn = new JSONArray();
        ReturnObject returnObject;
        if(!userObject.getUserModel().email.equals("mail@mail.com")){
            returnObject = CreateReturnObject.create(requestObject.getRequestModel().idRequest, 409);
        } else if(!userObject.getGeneralModel().nameObject.equals("admin")) {
            returnObject = CreateReturnObject.create(requestObject.getRequestModel().idRequest, 403);
        } else {
            returnObject = CreateReturnObject.create(requestObject.getRequestModel().idRequest, 201);
            UserObject newUserObject = createUserObject(userObject);
            arrJsReturn.put(newUserObject.toJSONObject());
        }
        arrJsReturn.put(returnObject.toJSONObject());

        return arrJsReturn.toString();
    }

    private static UserObject createUserObject(UserObject userObject){

        UserModel userModel = userObject.getUserModel();
        userModel.password = null;
        userModel.token = "test_token";
        userObject.setUserModel(userModel);

        GeneralModel generalModel = userObject.getGeneralModel();
        generalModel.idObject = 13;
        userObject.setGeneralModel(generalModel);

        return userObject;
    }
}
