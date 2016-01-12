package com.capture.buisneslogick.transport;

import com.capture.AppSoket;
import com.capture.buisneslogick.object.requestserver.RequestServerObject;
import com.capture.buisneslogick.transport.helper.OnCompliteTransportListner;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;

import org.json.JSONObject;

/**
 * Created by artem on 08.01.16.
 */
public class Transport {

    private OnCompliteTransportListner onCompliteTransportListner = null;
    private OnErrorTransportListner onErrorTransportListner = null;
    private long idRequest;

    public Transport(long id){
        idRequest = id;
    }

    public void send(JSONObject jsObj){
        AppSoket.getInstance().send(jsObj, idRequest, new AppSoket.OnCompliteListern() {
            @Override
            public void onComplite(JSONObject jsObj, RequestServerObject requestServerObject) {
                if ((requestServerObject.getRequestModul().getStatus() / 100) != 2) {
                    if(onErrorTransportListner != null){
                        onErrorTransportListner.onError(requestServerObject);
                    }
                } else {
                    if(onCompliteTransportListner != null){
                        onCompliteTransportListner.OnComplite(jsObj, requestServerObject);
                    }
                }
            }
        });
    }


    public void setOnCompliteListern(OnCompliteTransportListner onCompliteTransportListner){
        this.onCompliteTransportListner = onCompliteTransportListner;
    }

    public void setOnErrorTransportListner(OnErrorTransportListner onErrorTransportListner) {
        this.onErrorTransportListner = onErrorTransportListner;
    }
}
