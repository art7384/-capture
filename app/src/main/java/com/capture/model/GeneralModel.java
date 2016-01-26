package com.capture.model;

import com.capture.constant.ObjectRole;

/**
 * Created by artem on 30.12.15.
 */
public class GeneralModel extends BaseModel {

    public long idObject = -1;
    public String nameObject = null;
    public ObjectRole role = null;

    @Override
    public ModelType getModelType() {
        return ModelType.GENERAL;
    }

    public enum JsonKey {
        ID("id"),
        ROLE("role"),
        NAME("name");

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
