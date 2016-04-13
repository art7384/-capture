package com.capture.service.soket;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.capture.service.soket.event.OnSocketListner;

/**
 * Created by Кармишин on 12.04.2016.
 */
public abstract class BaseSocketService extends Service implements SocketService {

    private SocketBinder binder = new SocketBinder();
    protected OnSocketListner onSocketListner = null;
    protected Handler handler = new Handler();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void setOnSocketListner(OnSocketListner onSocketListner){
        this.onSocketListner = onSocketListner;
    }

    public class SocketBinder extends Binder {
        public SocketService getService() {
            return BaseSocketService.this;
        }
    }
}
