package com.capture.object;

import com.capture.model.BaseModel;
import com.capture.model.GeneralModel;
import com.capture.model.MessageModel;
import com.capture.object.common.BaseObject;

/**
 * Created by artem on 05.02.16.
 */
public class ErrorObject  extends BaseObject {

    private GeneralModel generalModel = new GeneralModel();
    private MessageModel messageModel = new MessageModel();

    public GeneralModel getGeneralModel(){
        return generalModel;
    }

    public void setGeneralModel(GeneralModel generalModel){
        this.generalModel = generalModel;
    }

    public MessageModel getMessageModel(){
        return messageModel;
    }

    public void setMessageModel(MessageModel messageModel){
        this.messageModel = messageModel;
    }

    @Override
    public BaseModel[] getModels() {
        return new BaseModel[]{generalModel, messageModel};
    }
}
