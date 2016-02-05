package com.capture.object.common;

import com.capture.buisneslogick.convector.GeneralConvector;
import com.capture.buisneslogick.convector.ModelConvector;
import com.capture.buisneslogick.convector.RequestConvector;
import com.capture.model.BaseModel;
import com.capture.model.ClientModel;
import com.capture.model.GeneralModel;
import com.capture.model.RequestModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 30.12.15.
 */
public abstract class BaseObject {
    abstract public BaseModel[] getModels();

    public JSONObject toJSONObject() {
        BaseModel[] models = getModels();
        JSONObject jsonObject = new JSONObject();
        for (BaseModel model : models) {

            String key = model.getModelType().toString();
            JSONObject jsModel = null;
            try {
                jsModel = ModelConvector.convectToJson(model);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (jsModel != null) {
                try {
                    jsonObject.put(key, jsModel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return jsonObject;
    }

    public enum Role {
        ERROR("error"),
        SCEN("scen"),
        USER("user");
        String role;

        Role(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return role;
        }

        static public Role get(String role) {
            if (role.equals(Role.USER.toString()))
                return Role.USER;
            if (role.equals(Role.SCEN.toString()))
                return Role.SCEN;
            return null;
        }
    }


}
