package com.capture.service.soket;

import android.util.Log;

import com.capture.AppSoket;
import com.capture.buisneslogick.service.UserService;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.Headers;
import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.WebSocket;

import java.util.List;
import java.util.Set;

/**
 * Created by artem on 29.12.15.
 */
public class WebSocketService extends BaseSocketService {

    private static final String LOG_TAG = "WebSocketService";
    private static final String SOCKET_URL = "http://game.4zdev.ru:8090";

    private WebSocket mWebSocket = null;

    private DataCallback mDataCallback = new DataCallback() {
        @Override
        public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
            Log.d(LOG_TAG, "I got some bytes!");
            Log.d(LOG_TAG, "emitter > " + emitter + " | bb > " + bb);
            bb.recycle();
            if(onSocketListner != null){
                onSocketListner.onDataAvailable(emitter, bb);
            }
        }
    };
    private CompletedCallback mCompletedCallback = new CompletedCallback() {
        @Override
        public void onCompleted(Exception e) {
            if(onSocketListner != null){
                onSocketListner.onCompleted(e);
            }
            mWebSocket = null;
        }
    };

    private AsyncHttpClient.WebSocketConnectCallback mWebSocketConnectCallback = new AsyncHttpClient.WebSocketConnectCallback(){
        @Override
        public void onCompleted(final Exception e, final WebSocket webSocket) {
            if (e != null) {
                e.printStackTrace();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (onSocketListner != null) {
                            onSocketListner.onErrorConnect(e);
                        }
                    }
                });
                return;
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d(LOG_TAG, "Соединение установлено!");

                    mWebSocket = webSocket;

                    webSocket.setStringCallback(mStringCallbacknew);
                    webSocket.setPongCallback(mPongCallback);
                    webSocket.setClosedCallback(mCompletedCallback);
                    webSocket.setDataCallback(mDataCallback);

                    if (onSocketListner != null) {
                        onSocketListner.onConnect();
                    }
                }
            });
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
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

    @Override
    public boolean isConnect(){
        if(mWebSocket == null) return false;
        return mWebSocket.isOpen();
    }

    @Override
    public void connect(){
        String token = UserService.getInstance().getUserObject().getUserModel().token;
        disconnect();
        AsyncHttpGet get = new AsyncHttpGet(SOCKET_URL);
        get.addHeader("app-version-name", AppSoket.getInstance().getPackageInfo().versionName);
        get.addHeader("app-version-code", "" + AppSoket.getInstance().getPackageInfo().versionCode);
        get.addHeader("token", token);
        log(get);
        AsyncHttpClient.getDefaultInstance().websocket(get, "http", mWebSocketConnectCallback);
    }

    private void log(AsyncHttpGet get){
        Log.d(LOG_TAG, "----- WEB SOCKET CONNECT -----");
        Log.d(LOG_TAG, "URL: " + get.getUri().toString());
        Headers header = get.getHeaders();
        Multimap multimap = header.getMultiMap();
        Set<String> keys = multimap.keySet();
        Log.d(LOG_TAG, "HEDERS:");
        for (String key : keys){
            List<String> vars = multimap.get(key);
            for(String var : vars){
                Log.d(LOG_TAG, "> " + key + " = " + var);
            }
        }
        Log.d(LOG_TAG, "----- END -----");
    }

    @Override
    public void disconnect(){
        if(mWebSocket != null){
            mWebSocket.close();
            mWebSocket = null;
        }
    }

    @Override
    public boolean send(String mess){
        if(mWebSocket != null && mWebSocket.isOpen()){
            mWebSocket.send(mess);
            return true;
        }
        return false;
    }




    private WebSocket.StringCallback mStringCallbacknew = new WebSocket.StringCallback() {
        @Override
        public void onStringAvailable(String s) {
            Log.d(LOG_TAG, "RECEIVING: " + s);
            if(onSocketListner != null){
                onSocketListner.onStringAvailable(s);
            }
        }
    };

    private WebSocket.PongCallback mPongCallback = new WebSocket.PongCallback() {
        @Override
        public void onPongReceived(String s) {
            Log.d(LOG_TAG, "PongCallback");
        }
    };









}
