package com.capture.buisneslogick.operation;

import com.capture.model.GeneralModel;
import com.capture.model.MessageModel;
import com.capture.object.ErrorObject;
import com.capture.object.common.BaseObject;

import java.util.Date;

/**
 * Created by artem on 05.02.16.
 */
public class CreateErrorObject {
    static public ErrorObject create(String tag, String message){

        ErrorObject errorObject = new ErrorObject();

        GeneralModel generalModel = new GeneralModel();
        generalModel.idObject = new Date().getTime();
        generalModel.role = BaseObject.Role.ERROR;
        generalModel.nameObject = tag;
        errorObject.setGeneralModel(generalModel);

        MessageModel messageModel = new MessageModel();
        messageModel.txt = message;
        errorObject.setMessageModel(messageModel);

        return errorObject;
    }
}
