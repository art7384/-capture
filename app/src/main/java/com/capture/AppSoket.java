package com.capture;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.capture.buisneslogick.service.ErrorService;
import com.capture.buisneslogick.service.RequestService;
import com.capture.buisneslogick.service.ReturnService;
import com.capture.object.ErrorObject;
import com.capture.object.ReturnObject;
import com.capture.object.request.RequestObject;
import com.capture.service.soket.ImulyatorSocketService;
import com.capture.service.soket.SocketService;
import com.capture.service.soket.WebSocketService;
import com.capture.service.soket.event.OnSocketListner;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by artem on 29.12.15.
 */
public class AppSoket extends Application implements OnSocketListner {

    private static final String LOG_TAG = "AppSoket";
    static private AppSoket sInstance = null;
    private SocketService mSocketService = null;
    private List<JSONObject> mMessagesList = new ArrayList<>();
    private PackageInfo packageInfo = null;
    private Map<Long, OnCompliteListern> mListner = new HashMap<>();
    private Handler mHamdler = new Handler();

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mSocketService = ((ImulyatorSocketService.SocketBinder) binder).getService();
            mSocketService.setOnSocketListner(AppSoket.getInstance());
        }

        public void onServiceDisconnected(ComponentName name) {
            mSocketService = null;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        Intent intent = new Intent(this, ImulyatorSocketService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    public static AppSoket getInstance() {
        return sInstance;
    }

    public PackageInfo getPackageInfo() {
        if (packageInfo == null) {
            PackageManager packageManager = getPackageManager();
            String packageName = getPackageName();
            try {
                packageInfo = packageManager.getPackageInfo(packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return packageInfo;
    }

    public boolean isConnect() {
        if (mSocketService == null) {
            return false;
        }
        return mSocketService.isConnect();
    }

    public void connectSocet() {
        mSocketService.connect();
    }

    public void send(JSONObject jsonObject, long id, OnCompliteListern listern) {
        mMessagesList.add(jsonObject);
        if (listern != null) {
            mListner.put(id, listern);
            mHamdler.postDelayed(new OldRequest(id), 30000);
        }
        try {
            send();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void send() throws JSONException {
        if (mSocketService != null) {
            if (mSocketService.isConnect()) {
                if (mMessagesList.size() > 0) {
                    JSONArray jsArr = new JSONArray();
                    jsArr.put(0, 0);
                    while (mMessagesList.size() > 0) {
                        jsArr.put(mMessagesList.remove(0));
                    }
                    Log.d(LOG_TAG, "SEND: " + jsArr);
                    mSocketService.send(jsArr.toString());
                }
            } else {
                mSocketService.connect();
            }
        }
    }

    /* =========== OnSocketListner ========== */

    @Override
    public void onConnect() {
        Intent intent = new Intent(KeyBroadcast.SOCET_UPDATE);
        intent.putExtra(KeyExtra.COMMAND.toString(), KeyEvent.CONNECT);
        sendBroadcast(intent);

        try {
            send();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompleted(Exception e) {
        String mess = "disconect";
        if(e != null && e.getMessage() != null){
            mess = e.getMessage();
        }
        Intent intent = new Intent();
        intent.putExtra(KeyExtra.MESSAGE.toString(), mess);
        intent.putExtra(KeyExtra.COMMAND.toString(), KeyEvent.COMPLITED);
        sendOrderedBroadcast(intent, KeyBroadcast.SOCET_UPDATE);
    }

    @Override
    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
//        TODO
        Intent intent = new Intent();
        sendOrderedBroadcast(intent, KeyBroadcast.SOCET_MESSAGE);
    }

    @Override
    public void onStringAvailable(String s) {
        JSONArray jsArr = null;
        try {
            jsArr = new JSONArray(s);
        } catch (JSONException e) {
            e.printStackTrace();
            sendError("parser", e.getMessage());
        }
        // сообщение должен быть json массив
        if (jsArr == null) return;
        arrayProcessing(jsArr);

        Intent intent = new Intent();
        sendOrderedBroadcast(intent, KeyBroadcast.SOCET_MESSAGE);
    }

    private void sendError(String tag, String mess) {
        ErrorObject errObj = ErrorService.getInstance().create(tag, mess);
        send(errObj.toJSONObject(), errObj.getGeneralModel().idObject, new OnCompliteListern() {
            @Override
            public void onComplite(JSONObject jsObj, ReturnObject returnObject) {
                //не нужен ответ
            }
        });
    }

    public void disconnect() {
        mSocketService.disconnect();
    }

    private void arrayProcessing(JSONArray jsArr) {
        for (int i = 0; i < jsArr.length(); i++) {
            JSONObject jsObj = null;
            try {
                jsObj = jsArr.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                sendError("parser", e.getMessage());
            }

            // массив состоит из json объектов (моделей)
            if (jsObj != null) {
                objectProcessing(jsObj);
            }
        }
    }

    private void objectProcessing(JSONObject jsObj) {

        if (ReturnService.getInstance().isReturnObject(jsObj)) {
            ReturnObject returnObject = new ReturnObject(jsObj);
            returnProcessing(jsObj, returnObject);
        } else if (RequestService.getInstance().isRequestObject(jsObj)) {
            RequestObject requestObject = new RequestObject(jsObj);
            ReturnObject returnObj = RequestService.getInstance().process(requestObject);
            send(returnObj.toJSONObject(), returnObj.getReturnModel().idRequest, new OnCompliteListern() {
                @Override
                public void onComplite(JSONObject jsObj, ReturnObject returnObject) {
                    //не нужен ответ
                }
            });
        } else {
            // TODO: json объект может быть часть игрового фото
        }

    }

    private void returnProcessing(JSONObject jsObj, ReturnObject returnObject) {
        long idRequest = returnObject.getReturnModel().idRequest;
        OnCompliteListern listern = mListner.remove(idRequest);
        if (listern != null) {
            // отправляем событие слушателю
            listern.onComplite(jsObj, returnObject);
        }
    }


    @Override
    public void onErrorConnect(Exception e) {
        Intent intent = new Intent();
        intent.putExtra(KeyExtra.MESSAGE.toString(), e.getMessage());
        intent.putExtra(KeyExtra.COMMAND.toString(), KeyEvent.ERROR_CONNECT);
        sendOrderedBroadcast(intent, KeyBroadcast.SOCET_UPDATE);
    }

    /* =========== END OnSocketListner =========== */

    public static class KeyBroadcast {
        static public final String SOCET_UPDATE = "com.capture.SOCET_UPDATE";
        static public final String SOCET_MESSAGE = "com.capture.SOCET_MESSAGE";
    }

    public enum KeyExtra {
        COMMAND("command"),
        MESSAGE("message");
        String key;

        KeyExtra(String key) {
            this.key = key;
        }

        public String toString() {
            return key;
        }
    }

    public static class KeyEvent {
        static public final Integer CONNECT = 0;
        static public final Integer COMPLITED = 1;
        static public final Integer ERROR_CONNECT = 2;
    }

     /* =========== EVENT =========== */

    public interface OnCompliteListern {
        void onComplite(JSONObject jsObj, ReturnObject returnObject);
    }

    private class OldRequest implements Runnable {
        private long idRequest;

        OldRequest(long id) {
            idRequest = id;
        }

        @Override
        public void run() {
            OnCompliteListern listern = mListner.remove(idRequest);
            if (listern != null) {
                ReturnObject returnObject = new ReturnObject();
                returnObject.getReturnModel().idRequest = idRequest;
                returnObject.getReturnModel().status = 408;
                returnObject.getReturnModel().text = "Request Timeout";
                listern.onComplite(null, returnObject);
            }
        }
    }

}
