package com.capture;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.capture.service.SocketService;

/**
 * Created by artem on 29.12.15.
 */
public class App extends Application {

    static private App sInstance = null;
    private SocketService mSocketService = null;
    private OnSocetServiceListner mOnSocetServiceListner = null;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mSocketService = ((SocketService.SocketBinder) binder).getService();
            if(mOnSocetServiceListner != null){
                mOnSocetServiceListner.onServiceConnected(mSocketService);
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            mSocketService = null;
            if(mOnSocetServiceListner != null){
                mOnSocetServiceListner.onServiceDisconnected();
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Intent intent = new Intent(this, SocketService.class);
        bindService(intent, mServiceConnection, 0);
        SocketService.start(this, "http://game.4zdev.ru:8090");
    }

    public static App getInstance() {
        return sInstance;
    }

    public SocketService getSocketService() {
        return mSocketService;
    }

    public SocketService setOnSocetServiceListner(OnSocetServiceListner onSocetServiceListner){
        mOnSocetServiceListner = onSocetServiceListner;
        return getSocketService();
    }

    interface OnSocetServiceListner {
        void onServiceConnected(SocketService socketService);
        void onServiceDisconnected();
    }
}
