package com.capture.service.soket;

import android.util.Log;

import com.capture.buisneslogick.convector.parser.ParserRequest;
import com.capture.model.RequestModel;
import com.capture.object.UserObject;
import com.capture.object.request.RequestObject;
import com.capture.service.soket.helpers.CreateReturnCreateUser;
import com.capture.service.soket.helpers.ImulyatorSocketHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Кармишин on 12.04.2016.
 */
public class ImulyatorSocketService extends BaseSocketService {

    private static final String LOG_TAG = "ImulyatorSocketService";
    private boolean mIsConnect = false;

    @Override
    public boolean isConnect() {
        return mIsConnect;
    }

    @Override
    public void connect() {
        if (!mIsConnect) {
            imulatorWebSocketConnectCallback();
            mIsConnect = true;
        }
    }

    @Override
    public void disconnect() {
        mIsConnect = false;
    }

    @Override
    public boolean send(String mess) {
        Log.d(LOG_TAG, "SEND: " + mess);
        analiz(mess);
        return true;
    }

    private void imulatorWebSocketConnectCallback() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "Соединение установлено!");
                if (onSocketListner != null) {
                    onSocketListner.onConnect();
                }
            }
        }, 500);
    }

    private void imulatorWebSocketStringCallbacknew(String s) {
        Log.d(LOG_TAG, "RECEIVING: " + s);
        if (onSocketListner != null) {
            onSocketListner.onStringAvailable(s);
        }
    }

    private void analiz(String mess) {

        JSONObject jsObjMess = ImulyatorSocketHelper.jsonFromMess(mess);
        RequestObject requestObject = new RequestObject(jsObjMess);
        //если это запрос, то на него необходимо ответить
        if(requestObject.getRequestModel() != null){
            // в зависимости от запроса формируем ответ
            switch (requestObject.getRequestModel().command) {
                case AUTHORIZATION:

                    break;
                case UNAUTHORIZED:

                    break;
                case EXIT:

                    break;
                case CREATE_SCEN:

                    break;
                case CONNECT_SCEN:

                    break;
                case GET_SCEN:

                    break;
                case CREATE_USER:
                     /*
        [0,{"request":{"command":"create_user","id":1460489142635},"general":{"name":"artem","role":"user"},"user":{"email":"art7384@gmail.com","password":"efe6398127928f1b2e9ef3207fb82663"}}]
    */
                    UserObject userObject = new UserObject(jsObjMess);
                    String returns = CreateReturnCreateUser.create(requestObject, userObject);
                    imulatorWebSocketStringCallbacknew(returns);
                    break;
            }
        }

    }

}
