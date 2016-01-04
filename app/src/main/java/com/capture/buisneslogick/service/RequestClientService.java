package com.capture.buisneslogick.service;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.capture.App;
import com.capture.buisneslogick.modul.ClientModul;
import com.capture.buisneslogick.modul.RequestModul;
import com.capture.buisneslogick.object.RequestClientObject;
import com.capture.service.SocketService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by artem on 29.12.15.
 */
public class RequestClientService {

    private static RequestClientService instance;

    private static final String LOG_TAG = "RequestClientService";
    private static final String COMMAND = "version";

    private String versionName = null;
    private int versionCode = 0;
    private String device;
    private String manufacturer;
    private static String OS = "Android";
    private String osVersion;
    private int sdk;

    private RequestClientService(){
        initDeviceData();
        initAppData();
    }

    public static RequestClientService getInstance() {
        if(instance == null){
            instance = new RequestClientService();
        }
        return instance;
    }

    /* ========== Function =========== */

    public void isСurrent() throws JSONException {
        RequestClientObject requestClient = create();
        isСurrent(requestClient);
    }

    private void isСurrent(RequestClientObject requestClientObject) throws JSONException {
        JSONObject jsonObject = requestClientObject.toJsonObject();
        App.getInstance().send(jsonObject);
    }

    private RequestClientObject create(){
        RequestClientObject requestClientObject = new RequestClientObject();
        ClientModul clientModul = requestClientObject.getClientModul();
        RequestModul requestModul = requestClientObject.getRequestModul();

        clientModul.setDevice(device);
        clientModul.setOs(osVersion);
        clientModul.setVersionClient(versionName);

        requestModul.setConnand(COMMAND);
        requestModul.setIdRequest(new Date().getTime());

        return requestClientObject;
    }

    /* ========== Helpers =========== */

    private void initDeviceData(){
        osVersion = Build.VERSION.RELEASE;
        sdk = Build.VERSION.SDK_INT;
        device = Build.MODEL;
        manufacturer = Build.MANUFACTURER;
    }

    private void initAppData(){
        App app = App.getInstance();
        PackageManager packageManager = app.getPackageManager();
        String packageName = app.getPackageName();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(packageInfo == null) return;

        versionName = packageInfo.versionName;
        versionCode = packageInfo.versionCode;
    }

}
