package com.capture.buisneslogick.transport.helper;

import com.capture.buisneslogick.object.requestserver.RequestServerObject;

/**
 * Created by artem on 08.01.16.
 */
public interface OnErrorTransportListner {
    void onError(RequestServerObject requestServerObject);
}
