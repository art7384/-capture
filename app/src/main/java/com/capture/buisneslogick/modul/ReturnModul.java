package com.capture.buisneslogick.modul;

import com.capture.model.ReturnModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Кармишин on 13.01.2016.
 */
public class ReturnModul implements BaseModul {

    ReturnModel model = new ReturnModel();

    public void setIdRequest(long id){
        model.idRequest = id;
    };
    public void setText(String txt){
        model.text = txt;
    };
    public void setStatus(int status){
        model.status = status;
    };

    public long getIdRequest(){
        return model.idRequest;
    };
    public String getText(){
        return model.text;
    };
    public int getStatus(){
        return model.status;
    };

    public void setReturnModul(ReturnModel model){
        this.model.idRequest = model.idRequest;
        this.model.status = model.status;
        this.model.text = model.text;
    }

    @Override
    public JSONObject toJsonObject() throws JSONException {
        return null;
    }

    @Override
    public String getJsonKey() {
        return "return";
    }
}
