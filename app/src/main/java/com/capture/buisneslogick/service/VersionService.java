package com.capture.buisneslogick.service;

/**
 * Created by artem on 29.12.15.
 */
public class VersionService {

    private String version = "0.1";

    private VersionService(){

    }

    public void checkSupport(OnCompliteListner onCompliteListner){

    }

    interface OnCompliteListner{
        void onComplite(Boolean version);
    }

}
