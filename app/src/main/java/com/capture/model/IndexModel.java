package com.capture.model;

/**
 * Created by artem on 27.01.16.
 */
public class IndexModel extends BaseModel {

    public long idObj = -1;

    @Override
    public ModelType getModelType() {
        return ModelType.INDEX;
    }

    public enum JsonKey {
        ID_OBJECT("idObject");

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
