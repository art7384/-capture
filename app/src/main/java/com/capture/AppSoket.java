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
import com.capture.buisneslogick.object.requestserver.RequestServerObject;
import com.capture.buisneslogick.convector.parser.object.RequestServerObjectParser;
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
 * Created by artem on 29.12.15.
 */
public class AppSoket extends Application implements SocketService.OnSocketListner {

    private static final String LOG_TAG = "AppSoket";
    static private AppSoket sInstance = null;
    private SocketService mSocketService = null;
    private List<JSONObject> mMessagesList = new ArrayList<>();
    private PackageInfo packageInfo = null;
    private Map<Long, OnCompliteListern> mListner = new HashMap<>();
    private Handler mHamdler = new Handler();

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mSocketService = ((SocketService.SocketBinder) binder).getService();
            mSocketService.setOnSocketListner(AppSoket.getInstance());
            //mSocketService.connect();
        }

        public void onServiceDisconnected(ComponentName name) {
            mSocketService = null;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        //Подключаем поддержку векторных изображений на Android4 (вектор поддерживается с Android 5)
        int[] ids = VectorDrawableCompat.findVectorResourceIdsByConvention(getResources(), R.drawable.class, Convention.RESOURCE_NAME_HAS_VECTOR_SUFFIX/*.ResourceNameHasVectorSuffix*/);
        VectorDrawableCompat.enableResourceInterceptionFor(getResources(), ids);

        sInstance = this;
        Intent intent = new Intent(this, SocketService.class);
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
        if (mSocketService == null) return false;
        return mSocketService.isConnect();
    }

    public void connectSocet() {
        mSocketService.connect();
    }

    public void send(JSONObject jsonObject, long id, OnCompliteListern listern) {
        mMessagesList.add(jsonObject);
        mListner.put(id, listern);
        mHamdler.postDelayed(new OldRequest(id), 30000);
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
        Intent intent = new Intent();
        intent.putExtra(KeyExtra.MESSAGE.toString(), e.getMessage());
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
        }
        // сообщение должен быть json массив
        if (jsArr == null) return;

        // бежим по массиву
        for (int i = 0; i < jsArr.length(); i++) {
            JSONObject jsObj = null;
            try {
                jsObj = jsArr.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // массив состоит из json объектов (моделей)
            if (jsObj != null) {
                try {
                    // json объект может быть либо ответом, либо запросом
                    RequestServerObject requestServerObject = RequestServerObjectParser.pars(jsObj);
                    if (requestServerObject != null) {
                        long idRequest = requestServerObject.getRequestModul().getIdRequest();
                        OnCompliteListern listern = mListner.remove(idRequest);
                        if (listern != null) {
                            // отправляем ответ на обработку
                            listern.onComplite(jsObj, requestServerObject);
                        }
                    } else {
                        // TODO: последующая обработка ответа
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        Intent intent = new Intent();
        sendOrderedBroadcast(intent, KeyBroadcast.SOCET_MESSAGE);
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
        void onComplite(JSONObject jsObj, RequestServerObject requestServerObject);
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
                RequestServerObject request = new RequestServerObject();
                request.getRequestModul().setIdRequest(idRequest);
                request.getRequestModul().setStatus(408);
                request.getRequestModul().setText("Request Timeout");
                listern.onComplite(null, request);
            }
        }
    }

}
