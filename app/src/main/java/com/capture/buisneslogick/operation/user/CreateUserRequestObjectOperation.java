package com.capture.buisneslogick.operation.user;

import com.capture.buisneslogick.modul.GeneralModul;
import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.modul.UserModul;
import com.capture.object.request.UserRequestObject;
import com.capture.constant.ObjectRole;
import com.capture.constant.RequestCommand;
import com.capture.model.UserModel;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by artem on 08.01.16.
 */
public class CreateUserRequestObjectOperation {

    static public UserRequestObject createRegistration(UserModel userModel, String nick) throws NoSuchAlgorithmException {

        UserRequestObject object = new UserRequestObject();

        RequestModul request = object.getRequestModul();
        request.setConnand(RequestCommand.CREATE_USER);
        request.setIdRequest(new Date().getTime());

        GeneralModul general = object.getGeneralModel();
        general.setNameObject(nick);
        general.setRole(ObjectRole.USER);

        UserModul user = object.getUserModul();
        user.setEmail(userModel.email);
        user.setPassword(userModel.password);

        return object;

    }

    static public UserRequestObject createAthorization(UserModel userModel) throws NoSuchAlgorithmException {

        UserRequestObject object = new UserRequestObject();

        RequestModul request = object.getRequestModul();
        request.setConnand(RequestCommand.AUTHORIZATION);
        request.setIdRequest(new Date().getTime());

        GeneralModul general = object.getGeneralModel();
        general.setRole(ObjectRole.USER);

        UserModul user = object.getUserModul();
        user.setEmail(userModel.email);
        user.setPassword(userModel.password);

        return object;

    }


}
