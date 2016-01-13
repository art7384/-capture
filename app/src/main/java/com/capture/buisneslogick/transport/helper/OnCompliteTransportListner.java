package com.capture.buisneslogick.transport.helper;

import com.capture.object.ReturnObject;

import org.json.JSONObject;

/**
 * Created by artem on 08.01.16.
 */
public interface OnCompliteTransportListner {
    void OnComplite(JSONObject jsObj, ReturnObject returnObject);
}
