package com.capture.service.soket.helpers;

import com.capture.model.RequestModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Кармишин on 12.04.2016.
 */
public class ImulyatorSocketHelper {


    /**
     * <p>Преобразует сообщение сервера в jsonObject.</p>
     * <p>На сервер уходит сообщение внутри массива вида:
     * [int, JSONObject]
     * метод парсит строку в json массив и возвращает json сообщения из массива</p>
     *
     * @param mess Сообщение серверу
     * @return jsonObject сообщение
     */
    static public JSONObject jsonFromMess(String mess){
        JSONArray jsArrMess = null;
        try {
            jsArrMess = new JSONArray(mess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsObjMess = null;
        try {
            jsObjMess = jsArrMess.getJSONObject(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsObjMess;
    }

}
