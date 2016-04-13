package com.capture.service.soket;

import com.capture.service.soket.event.OnSocketListner;

/**
 * Created by Кармишин on 12.04.2016.
 */
public interface SocketService {
    boolean isConnect();
    void connect();
    void disconnect();
    boolean send(String mess);
    void setOnSocketListner(OnSocketListner onSocketListner);
}
