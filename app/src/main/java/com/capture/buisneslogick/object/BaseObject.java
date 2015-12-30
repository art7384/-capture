package com.capture.buisneslogick.object;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 30.12.15.
 */
public abstract class BaseObject {
    abstract JSONObject toJsonObject() throws JSONException;
}
