package com.capture.buisneslogick.operation;

import com.capture.model.GeneralModel;
import com.capture.model.RequestModel;
import com.capture.model.UserModel;
import com.capture.object.common.BaseObject;
import com.capture.object.request.AuthorizationRequestObject;
import com.capture.object.request.RegistrationRequestObject;
import com.capture.object.request.RequestObject;

import java.util.Date;

/**
 * Created by artem on 26.01.16.
 */
public class CreaterAuthorizationRequestObject {

    static public AuthorizationRequestObject create(UserModel user) {

        AuthorizationRequestObject authorizationObj = new AuthorizationRequestObject();

        RequestModel request = new RequestModel();
        request.command = RequestObject.Command.AUTHORIZATION;
        request.idRequest = new Date().getTime();

        authorizationObj.setRequestModel(request);
        authorizationObj.setUserModel(user);

        return authorizationObj;
    }
}
