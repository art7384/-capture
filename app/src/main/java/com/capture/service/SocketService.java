package com.capture.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.capture.AppSoketTest;
import com.capture.buisneslogick.service.UserService;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.WebSocket;

/**
 * Created by artem on 29.12.15.
 */
public class SocketService extends Service {

    private static final String LOG_TAG = "SocketService";
    private static final String SOCKET_URL = "http://game.4zdev.ru:8090";
    private OnSocketListner mOnSocketListner = null;
    private WebSocket mWebSocket = null;
    private Handler mHandler;
    SocketBinder binder = new SocketBinder();

    private WebSocket.StringCallback mStringCallbacknew = new WebSocket.StringCallback() {
        @Override
        public void onStringAvailable(String s) {
            Log.d(LOG_TAG, "RECEIVING: " + s);
            if(mOnSocketListner != null){
                mOnSocketListner.onStringAvailable(s);
            }
        }
    };

    private WebSocket.PongCallback mPongCallback = new WebSocket.PongCallback() {
        @Override
        public void onPongReceived(String s) {
            Log.d(LOG_TAG, "PongCallback");
        }
    };

    private CompletedCallback mCompletedCallback = new CompletedCallback() {
        @Override
        public void onCompleted(Exception e) {
            if(mOnSocketListner != null){
                mOnSocketListner.onCompleted(e);
            }
            mWebSocket = null;
        }
    };

    private DataCallback mDataCallback = new DataCallback() {
        @Override
        public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
            Log.d(LOG_TAG, "I got some bytes!");
            Log.d(LOG_TAG, "emitter > " + emitter + " | bb > " + bb);
            bb.recycle();
            if(mOnSocketListner != null){
                mOnSocketListner.onDataAvailable(emitter, bb);
            }
        }
    };

    private AsyncHttpClient.WebSocketConnectCallback mWebSocketConnectCallback = new AsyncHttpClient.WebSocketConnectCallback(){
        @Override
        public void onCompleted(final Exception e, final WebSocket webSocket) {
            if (e != null) {
                e.printStackTrace();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mOnSocketListner != null) {
                            mOnSocketListner.onErrorConnect(e);
                        }
                    }
                });
                return;
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d(LOG_TAG, "Соединение установлено!");

                    mWebSocket = webSocket;

                    webSocket.setStringCallback(mStringCallbacknew);
                    webSocket.setPongCallback(mPongCallback);
                    webSocket.setClosedCallback(mCompletedCallback);
                    webSocket.setDataCallback(mDataCallback);

                    if (mOnSocketListner != null) {
                        mOnSocketListner.onConnect();
                    }
                }
            });
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public boolean isConnect(){
        if(mWebSocket == null) return false;
        return mWebSocket.isOpen();
    }

    public void connect(){
        String token = UserService.getInstance().getUserObject().getUserModel().tocken;
        disconnect();
        AsyncHttpGet get = new AsyncHttpGet(SOCKET_URL);
        //get.addHeader("os", "Android");
        //get.addHeader("os-version", Build.VERSION.RELEASE);
        //get.addHeader("sdk", "" + Build.VERSION.SDK_INT);
        //get.addHeader("model", Build.MODEL);
        //get.addHeader("manufacturer", Build.MANUFACTURER);
        get.addHeader("app-version-name", AppSoketTest.getInstance().getPackageInfo().versionName);
        get.addHeader("app-version-code", "" + AppSoketTest.getInstance().getPackageInfo().versionCode);
        get.addHeader("token", token);
        AsyncHttpClient.getDefaultInstance().websocket(get, "http", mWebSocketConnectCallback);
    }

    public void disconnect(){
        if(mWebSocket != null){
            mWebSocket.close();
            mWebSocket = null;
        }
    }

    static public void stop(Context context) {
        context.stopService(new Intent(context, SocketService.class));
    }

    public boolean send(String mess){
        if(mWebSocket != null && mWebSocket.isOpen()){
            mWebSocket.send(mess);
            return true;
        }
        return false;
    }

    public void setOnSocketListner(OnSocketListner onSocketListner){
        mOnSocketListner = onSocketListner;
    }

    public interface OnSocketListner{
        void onConnect();
        void onCompleted(Exception e);
        void onDataAvailable(DataEmitter emitter, ByteBufferList bb);
        void onStringAvailable(String s);
        void onErrorConnect(Exception e);
    }

     public class SocketBinder extends Binder {
        public SocketService getService() {
            return SocketService.this;
        }
    }

}
