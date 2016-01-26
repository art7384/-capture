package com.capture.model;

/**
 * Created by Кармишин on 13.01.2016.
 */
public class ReturnModel extends BaseModel {

    public long idRequest = -1;
    public String text = null;
    public int status = -1;

    @Override
    public ModelType getModelType() {
        return ModelType.RETURN;
    }

    public enum JsonKey {
        TEXT("text"),
        STATUS("status"),
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
