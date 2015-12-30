package com.capture.buisneslogick.service;

import android.app.Service;

import com.capture.App;
import com.capture.buisneslogick.modul.ClientModul;
import com.capture.buisneslogick.modul.GeneralModul;
import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.object.RequestClientObject;
import com.capture.buisneslogick.transport.ClientTransport;
import com.capture.model.GeneralModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by artem on 29.12.15.
 */
public class RequestClientService {

    private String version = "0.1";
    private String device = "";
    private String os = "";

    private RequestClientService(){

    }

    public void isСurrent(RequestClientObject requestClientObject, OnCompliteListner onCompliteListner) throws JSONException {
        JSONObject jsonObject = requestClientObject.toJsonObject();
        byte[] mess = ("[0," + jsonObject.toString() + "]").getBytes();
        App.getInstance().getSocketService().send(mess);
    }

    public RequestClientObject create(){
        RequestClientObject requestClientObject = new RequestClientObject();
        ClientModul clientModul = requestClientObject.getClientModul();
        RequestModul requestModul = requestClientObject.getRequestModul();

        clientModul.setDevice(device);
        clientModul.setOs(os);
        clientModul.setVersionClient(version);

        requestModul.setConnand("version");
        requestModul.setIdRequest(new Date().getTime());

        return requestClientObject;
    }

    interface OnCompliteListner{
        void onComplite(Boolean version);
    }

}
