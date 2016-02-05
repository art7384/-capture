package com.capture.buisneslogick.service;

import com.capture.buisneslogick.operation.CreateErrorObject;
import com.capture.model.GeneralModel;
import com.capture.object.ErrorObject;
import com.capture.object.common.BaseObject;

import java.util.Date;

/**
 * Created by artem on 05.02.16.
 */
public class ErrorService {

    private static ErrorService instance = null;
    private ErrorService() {

    }

    public static ErrorService getInstance() {
        if (instance == null) {
            instance = new ErrorService();
        }
        return instance;
    }

    public ErrorObject create(String tag, String mess){

        return CreateErrorObject.create(tag, mess);
    }

}
