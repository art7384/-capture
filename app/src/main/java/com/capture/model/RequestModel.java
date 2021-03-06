package com.capture.model;

import com.capture.object.request.RequestObject;

/**
 * Created by artem on 30.12.15.
 */
public class RequestModel extends BaseModel{
    public RequestObject.Command command = null;
    public long idRequest = -1;

    @Override
    public ModelType getModelType() {
        return ModelType.REQUEST;
    }

    public enum JsonKey {
        COMMAND("command"),
        ID("id");

        String key;

        JsonKey(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return key;
        }

    }

}
