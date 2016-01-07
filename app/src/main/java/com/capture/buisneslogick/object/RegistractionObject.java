package com.capture.buisneslogick.object;

import com.capture.buisneslogick.modul.ClientModul;
import com.capture.buisneslogick.modul.GeneralModul;
import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.modul.UserModul;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 06.01.16.
 */
public class RegistractionObject extends BaseObject {

    private final GeneralModul generalModul = new GeneralModul();
    private final UserModul userModul = new UserModul();
    private final RequestModul requestModul = new RequestModul();


    public RequestModul getRequestModul(){
        return requestModul;
    }

    public GeneralModul getGeneralModel(){
        return generalModul;
    }

    public UserModul getUserModul(){
        return userModul;
    }

    @Override
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(requestModul.getJsonKey(), requestModul.toJsonObject());
        jsonObject.put(generalModul.getJsonKey(), generalModul.toJsonObject());
        jsonObject.put(userModul.getJsonKey(), userModul.toJsonObject());
        return jsonObject;
    }
}
