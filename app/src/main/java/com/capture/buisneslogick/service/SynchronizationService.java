package com.capture.buisneslogick.service;

import com.capture.buisneslogick.persisten.UserProfile;
import com.capture.object.GeneralObject;
import com.capture.object.UserObject;

import org.json.JSONObject;

/**
 * Created by artem on 13.04.16.
 */
public class SynchronizationService {

    static private SynchronizationService instance = null;

    private SynchronizationService(){

    }

    public static SynchronizationService getInstance() {
        if(instance == null){
            instance = new SynchronizationService();
        }
        return instance;
    }

    public void synchronization(JSONObject jsonObject){
        GeneralObject generalObject = new GeneralObject(jsonObject);
        switch (generalObject.getGeneralModel().role){
            case USER: {
                UserObject userObject = new UserObject(jsonObject);
                UserProfile.getInstance().save(userObject);
                break;
            }
        }

    }



}
