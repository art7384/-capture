package com.capture.buisneslogick.modul;

import com.capture.buisneslogick.convector.GeneralConvector;
import com.capture.model.GeneralModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 30.12.15.
 */
public class GeneralModul implements BaseModul {

    private GeneralModel model = new GeneralModel();

    @Override
    public JSONObject toJsonObject() throws JSONException {
        return GeneralConvector.convectToJson(model);
    }

    @Override
    public String getJsonKey() {
        return "general";
    }

    public long getIdObject(){
        return model.idObject;
    }
    public String getNameObject(){
        return model.nameObject;
    }
    public String getRole(){
        return model.role;
    }

    public void setIdObject(long id){
        model.idObject = id;
    }
    public void setNameObject(String name){
        model.nameObject = name;
    }
    public void setRole(String role){
        model.role = role;
    }

}
