package com.capture.service.soket.helpers;

import com.capture.buisneslogick.operation.CreateReturnObject;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;
import com.capture.object.ReturnObject;
import com.capture.object.UserObject;
import com.capture.object.common.BaseObject;
import com.capture.object.request.RequestObject;

import org.json.JSONArray;

/**
 * Created by artem on 13.04.16.
 */
public class CreateReturnAuthorization {

    static public String create(RequestObject requestObject, UserObject userObject){
        JSONArray arrJsReturn = new JSONArray();
        ReturnObject returnObject;
        if(!userObject.getUserModel().email.equals("mail@mail.com")){
            returnObject = CreateReturnObject.create(requestObject.getRequestModel().idRequest, 404);
        } else if(!userObject.getUserModel().password.equals("efe6398127928f1b2e9ef3207fb82663")) {
            returnObject = CreateReturnObject.create(requestObject.getRequestModel().idRequest, 404);
        } else {
            returnObject = CreateReturnObject.create(requestObject.getRequestModel().idRequest, 200);
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

        GeneralModel generalModel = new GeneralModel();
        generalModel.idObject = 13;
        generalModel.nameObject = "admin";
        generalModel.role = BaseObject.Role.USER;
        userObject.setGeneralModel(generalModel);

        return userObject;
    }
}
