package com.capture.buisneslogick.modul;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 30.12.15.
 */
public interface BaseModul {
    JSONObject toJsonObject() throws JSONException;
    String getJsonKey();
}
