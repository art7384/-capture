package com.capture.buisneslogick.service;

import com.capture.buisneslogick.operation.CreateReturnObject;
import com.capture.model.RequestModel;
import com.capture.model.ReturnModel;
import com.capture.object.ReturnObject;
import com.capture.object.request.RequestObject;

import org.json.JSONObject;

/**
 * Created by artem on 04.02.16.
 */
public class RequestService {

    private static RequestService instance = null;

    private RequestService() {

    }

    public static RequestService getInstance() {
        if (instance == null) {
            instance = new RequestService();
        }
        return instance;
    }

    public ReturnObject process(RequestObject requestObject) {
        RequestModel requestModel = requestObject.getRequestModel();
        if (requestModel.command == RequestObject.Command.UNAUTHORIZED) {
            unauthorized();
            return CreateReturnObject.create(requestModel.idRequest, 200);
        }
        return CreateReturnObject.create(requestModel.idRequest, 400);

    }

    public boolean isRequestObject(JSONObject jsObj){
        return !jsObj.isNull(new RequestModel().getModelType().toString());
    }

    private void unauthorized() {
//            TODO сообщить пользователю о неудачной попытки залогинится
    }

}
