package com.capture.buisneslogick.operation.common;

import com.capture.buisneslogick.modul.RequestModul;
import com.capture.object.request.RequestObject;
import com.capture.constant.RequestCommand;

import java.util.Date;

/**
 * Created by artem on 09.01.16.
 */
public class CreateRequestOperation {

    static public RequestObject createExit() {
        RequestObject object = new RequestObject();

        RequestModul request = object.getRequestModul();
        request.setConnand(RequestCommand.EXIT);
        request.setIdRequest(new Date().getTime());

        return object;
    }

}
