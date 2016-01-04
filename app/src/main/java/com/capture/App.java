package com.capture;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;

import com.capture.service.SocketService;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 29.12.15.
 */
public class App extends Application implements SocketService.OnSocketListner {

    private static final String LOG_TAG = "App";
    static private App sInstance = null;
    private SocketService mSocketService = null;
    private List<JSONObject> mMessagesList = new ArrayList<>();
    private PackageInfo packageInfo = null;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mSocketService = ((SocketService.SocketBinder) binder).getService();
            mSocketService.setOnSocketListner(App.getInstance());
            mSocketService.connect();
        }

        public void onServiceDisconnected(ComponentName name) {
            mSocketService = null;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Intent intent = new Intent(this, SocketService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    public static App getInstance() {
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

    public void send(JSONObject jsonObject) {
        mMessagesList.add(jsonObject);
        send();
    }

    private void send() {
        if (mSocketService != null) {
            if (mSocketService.isConnect()) {
                if (mMessagesList.size() > 0) {
                    String mess = "[0,";
                    while (mMessagesList.size() > 0) {
                        mess += mMessagesList.remove(0).toString() + ",";
                    }
                    mess += "]";
                    Log.d(LOG_TAG, "send " + mess);
                    byte[] bytes = mess.getBytes();
                    mSocketService.send(bytes);
                }
            } else {
                mSocketService.connect();
            }
        }
    }

    /* =========== OnSocketListner ========== */

    @Override
    public void onConnect() {
        send();
    }

    @Override
    public void onCompleted(Exception e) {

    }

    @Override
    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {

    }

    @Override
    public void onStringAvailable(String s) {

    }

    @Override
    public void onErrorConnect(Exception e) {

    }

    /* =========== END OnSocketListner =========== */

}
