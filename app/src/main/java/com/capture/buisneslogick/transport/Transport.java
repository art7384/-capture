package com.capture.buisneslogick.transport;

import com.capture.AppSoket;
import com.capture.buisneslogick.transport.helper.OnCompliteTransportListner;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;
import com.capture.object.ReturnObject;

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
            public void onComplite(JSONObject jsObj, ReturnObject returnObject) {
                if ((returnObject.getReturnModul().getStatus() / 100) != 2) {
                    if(onErrorTransportListner != null){
                        onErrorTransportListner.onError(returnObject);
                    }
                } else {
                    if(onCompliteTransportListner != null){
                        onCompliteTransportListner.OnComplite(jsObj, returnObject);
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
