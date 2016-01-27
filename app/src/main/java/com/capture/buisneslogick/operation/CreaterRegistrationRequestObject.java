package com.capture.buisneslogick.operation;

import com.capture.model.GeneralModel;
import com.capture.model.RequestModel;
import com.capture.model.UserModel;
import com.capture.object.common.BaseObject;
import com.capture.object.request.RegistrationRequestObject;
import com.capture.object.request.RequestObject;

import java.util.Date;

/**
 * Created by artem on 26.01.16.
 */
public class CreaterRegistrationRequestObject {

    static public RegistrationRequestObject create(UserModel user, String nick) {

        RegistrationRequestObject requestObj = new RegistrationRequestObject();

        RequestModel request = new RequestModel();
        request.command = RequestObject.Command.CREATE_USER;
        request.idRequest = new Date().getTime();

        GeneralModel general = new GeneralModel();
        general.role = BaseObject.Role.USER;
        general.nameObject = nick;

        requestObj.setRequestModel(request);
        requestObj.setUserModel(user);
        requestObj.setGeneralModel(general);

        return requestObj;
    }
}
