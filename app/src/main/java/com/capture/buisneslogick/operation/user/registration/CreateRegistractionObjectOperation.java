package com.capture.buisneslogick.operation.user.registration;

import com.capture.buisneslogick.modul.GeneralModul;
import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.modul.UserModul;
import com.capture.buisneslogick.object.RegistractionObject;
import com.capture.model.UserModel;
import com.capture.role.Role;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by artem on 08.01.16.
 */
public class CreateRegistractionObjectOperation {

    static public RegistractionObject create(UserModel userModel, String nick) throws NoSuchAlgorithmException {

        RegistractionObject object = new RegistractionObject();

        RequestModul request = object.getRequestModul();
        request.setConnand("create_user");
        request.setIdRequest(new Date().getTime());

        GeneralModul general = object.getGeneralModel();
        general.setNameObject(nick);
        general.setRole(Role.USER);

        UserModul user = object.getUserModul();
        user.setEmail(userModel.email);
        user.setPassword(userModel.password);

        return object;

    }

}
