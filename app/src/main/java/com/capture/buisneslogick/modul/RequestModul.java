package com.capture.buisneslogick.modul;

import com.capture.buisneslogick.convector.RequestConvector;
import com.capture.constant.RequestCommand;
import com.capture.model.RequestModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 30.12.15.
 */
public class RequestModul implements BaseModul {

    private RequestModel model = new RequestModel();

    @Override
    public JSONObject toJsonObject() throws JSONException {
        return RequestConvector.convectToJson(model);
    }

    @Override
    public String getJsonKey() {
        return "request";
    }

    public RequestCommand getCommand(){
        return model.command;
    }

    public void setConnand(RequestCommand command){
        model.command = command;
    }

    public long getIdRequest(){
        return model.idRequest;
    }
    public void setIdRequest(long id){
model.idRequest = id;
    }

    public void setStatus(int status){
        model.status = status;
    }

    public int getStatus(){
        return model.status;
    }

    public void setText(String text){
        model.text = text;
    }

    public String getText(){
        return model.text;
    }

}
