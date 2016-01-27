package com.capture.model;

/**
 * Created by artem on 27.01.16.
 */
public class MessageModel extends BaseModel {

    public String txt = null;

    @Override
    public ModelType getModelType() {
        return ModelType.MESSAGE;
    }

    public enum JsonKey {
        TEXT("text");

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
