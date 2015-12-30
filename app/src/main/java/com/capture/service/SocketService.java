package com.capture.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

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
    private static final String EXTRA_URL = "extra_url";
    private OnSocketListner mOnSocketListner = null;
    private WebSocket mWebSocket = null;
    private Handler mHandler;
    SocketBinder binder = new SocketBinder();

    private WebSocket.StringCallback mStringCallbacknew = new WebSocket.StringCallback() {
        @Override
        public void onStringAvailable(String s) {
            Log.d(LOG_TAG, "I got a string: " + s);
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

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");

        String url = intent.getStringExtra(EXTRA_URL);
        startSocet(url);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    static public void start(Context context, String url){
        stop(context);
        Intent intent = new Intent(context, SocketService.class);
        intent.putExtra(EXTRA_URL, url);
        context.startService(intent);
    }
    static public void stop(Context context) {
        context.stopService(new Intent(context, SocketService.class));
    }

    private boolean send(byte[] mess){
        if(mWebSocket != null && mWebSocket.isOpen()){
            mWebSocket.send(mess);
            return true;
        }
        return false;
    }

    private void startSocet(String url) {
        AsyncHttpGet get = new AsyncHttpGet(url);
        //get.addHeader("X-token", "tocken");
        AsyncHttpClient.getDefaultInstance().websocket(get, "http", mWebSocketConnectCallback);
    }

    public void setOnSocketListner(OnSocketListner onSocketListner){
        mOnSocketListner = onSocketListner;
    }

    public interface OnSocketListner{
        void onConnect();
        void onCompleted(Exception e);
        void onDataAvailable(DataEmitter emitter, ByteBufferList bb);
        void onErrorConnect(Exception e);
    }

     public class SocketBinder extends Binder {
        public SocketService getService() {
            return SocketService.this;
        }
    }

}
