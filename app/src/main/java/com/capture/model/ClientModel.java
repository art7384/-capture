package com.capture.model;

/**
 * Created by artem on 30.12.15.
 */
public class ClientModel extends BaseModel {
    public String os = null;
    public String versionClient = null;
    public String device = null;

    @Override
    public ModelType getModelType() {
        return ModelType.CLIENT;
    }

    public enum JsonKey {
        DEVICE("device"),
        OS("os"),
        VERSION("version");

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
