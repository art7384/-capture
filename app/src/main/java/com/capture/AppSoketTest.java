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

import com.bettervectordrawable.Convention;
import com.bettervectordrawable.VectorDrawableCompat;
import com.capture.buisneslogick.service.ErrorService;
import com.capture.buisneslogick.service.RequestService;
import com.capture.buisneslogick.service.ReturnService;
import com.capture.object.ErrorObject;
import com.capture.object.ReturnObject;
import com.capture.object.request.RequestObject;
import com.capture.service.SocketService;
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
 * Created by Кармишин on 04.04.2016.
 */
public class AppSoketTest extends Application {

    private static final String LOG_TAG = "AppSoketTest";
    static private AppSoketTest sInstance = null;
    private PackageInfo packageInfo = null;
    private Map<Long, OnCompliteListern> mListner = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public static AppSoketTest getInstance() {
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
        return true;
    }

    public void connectSocet() {
    }

    public void send(JSONObject jsonObject, long id, OnCompliteListern listern) {
        Log.d(LOG_TAG, "SEND: " + jsonObject);
        String comand = null;
        try {
            comand = jsonObject.getJSONObject("request").getString("command");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        if(comand.equals("authorization")){
//            arrayProcessing("[{request:}]");
//        } else {
//
//        }

//        mMessagesList.add(jsonObject);

       /// {"request":{"command":"authorization","id":1459798699685},"user":{"email":"апро","password":"774f25a2c7c783cb5186396f201f401e"}}


    }


    public void disconnect() {
    }

    private void arrayProcessing(JSONArray jsArr) {
        for (int i = 0; i < jsArr.length(); i++) {
            JSONObject jsObj = null;
            try {
                jsObj = jsArr.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
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


}
