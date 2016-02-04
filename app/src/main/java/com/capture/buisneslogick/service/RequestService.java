package com.capture.buisneslogick.service;

import com.capture.AppSoket;
import com.capture.buisneslogick.operation.CreateReturnObject;
import com.capture.model.RequestModel;
import com.capture.model.ReturnModel;
import com.capture.object.ReturnObject;
import com.capture.object.request.RequestObject;

/**
 * Created by artem on 04.02.16.
 */
public class RequestService {

    private static RequestService instance = null;

    private RequestService(){

    }

    public static RequestService getInstance() {
        if(instance == null){
            instance = new RequestService();
        }
        return instance;
    }

    public void process(RequestObject requestObject){
        ReturnObject returnObject = null;
        RequestModel requestModel = requestObject.getRequestModel();
        if(requestModel.command == RequestObject.Command.UNAUTHORIZED){
            returnObject = CreateReturnObject.create(requestModel.idRequest, 200);
//            TODO сообщить пользователю о неудачной попытки залогинится

        } else {
            returnObject = CreateReturnObject.create(requestModel.idRequest, 400);
        }
        AppSoket.getInstance().send(returnObject.toJSONObject());
    }

}
