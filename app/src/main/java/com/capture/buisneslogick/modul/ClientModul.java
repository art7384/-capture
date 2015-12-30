package com.capture.buisneslogick.modul;

import com.capture.buisneslogick.convector.ClientConvector;
import com.capture.model.ClientModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 30.12.15.
 */
public class ClientModul implements BaseModul {

    private ClientModel model = new ClientModel();

    @Override
    public JSONObject toJsonObject() throws JSONException {
        return ClientConvector.convectToJson(model);
    }

    @Override
    public String getJsonKey() {
        return "client";
    }

//    public String getOs() {
//        return model.os;
//    }

//    public String getVersion() {
//        return model.versionClient;
//    }

//    public String getDevice() {
//        return model.device;
//    }

    public void setOs(String os){
        model.os = os;
    }

    public void setVersionClient(String version){
        model.versionClient = version;
    }

    public void setDevice(String device){
        model.device = device;
    }

}
