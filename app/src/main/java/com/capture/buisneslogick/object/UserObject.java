package com.capture.buisneslogick.object;

import com.capture.buisneslogick.modul.GeneralModul;
import com.capture.buisneslogick.modul.UserModul;
import com.capture.buisneslogick.object.common.BaseObject;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 07.01.16.
 */
public class UserObject extends BaseObject {

    private final GeneralModul generalModul = new GeneralModul();
    private final UserModul userModul = new UserModul();

    public void setUserModel(UserModel user){
        userModul.setEmail(user.email);
        userModul.setTocken(user.tocken);
    }

    public UserModul getUserModul(){
        return userModul;
    }

    public GeneralModul getGeneralModul(){
        return generalModul;
    }

    public void setGeneralModel(GeneralModel general){
        generalModul.setNameObject(general.nameObject);
        generalModul.setIdObject(general.idObject);
        generalModul.setRole(general.role);
    }

    @Override
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(generalModul.getJsonKey(), generalModul.toJsonObject());
        jsonObject.put(userModul.getJsonKey(), userModul.toJsonObject());
        return jsonObject;
    }
}
