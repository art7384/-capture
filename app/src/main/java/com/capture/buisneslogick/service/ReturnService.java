package com.capture.buisneslogick.service;

import com.capture.model.ReturnModel;

import org.json.JSONObject;

/**
 * Created by artem on 05.02.16.
 */
public class ReturnService {

    private static ReturnService instance = null;

    private ReturnService() {

    }

    public static ReturnService getInstance() {
        if (instance == null) {
            instance = new ReturnService();
        }
        return instance;
    }

    public boolean isReturnObject(JSONObject jsObj){
        return !jsObj.isNull(new ReturnModel().getModelType().toString());
    }

}
