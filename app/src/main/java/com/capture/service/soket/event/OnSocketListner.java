package com.capture.service.soket.event;

import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;

/**
 * Created by Кармишин on 12.04.2016.
 */
public interface OnSocketListner {
    void onConnect();
    void onCompleted(Exception e);
    void onDataAvailable(DataEmitter emitter, ByteBufferList bb);
    void onStringAvailable(String s);
    void onErrorConnect(Exception e);
}
