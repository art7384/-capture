package com.capture.object.request;

import com.capture.buisneslogick.modul.GeneralModul;
import com.capture.buisneslogick.modul.UserModul;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 06.01.16.
 */
public class UserRequestObject extends RequestObject {

    private final GeneralModul generalModul = new GeneralModul();
    private final UserModul userModul = new UserModul();

    public GeneralModul getGeneralModel(){
        return generalModul;
    }

    public UserModul getUserModul(){
        return userModul;
    }

    @Override
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put(generalModul.getJsonKey(), generalModul.toJsonObject());
        jsonObject.put(userModul.getJsonKey(), userModul.toJsonObject());
        return jsonObject;
    }
}
