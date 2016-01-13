package com.capture.object;

import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.modul.ReturnModul;
import com.capture.model.ReturnModel;
import com.capture.object.common.BaseObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Кармишин on 13.01.2016.
 */
public class ReturnObject extends BaseObject {

    private ReturnModul requestModul = new ReturnModul();

    @Override
    public JSONObject toJsonObject() throws JSONException {
        return null;
    }
    public ReturnModul getReturnModul(){
        return requestModul;
    }
}
