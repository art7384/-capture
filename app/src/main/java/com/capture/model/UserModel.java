package com.capture.model;

/**
 * Created by artem on 06.01.16.
 */
public class UserModel extends BaseModel {
    public String email = null;
    public String password = null;
    public String token = null;

    @Override
    public ModelType getModelType() {
        return ModelType.USER;
    }

    public enum JsonKey {
        EMAIL("email"),
        TOKEN("token"),
        PASSWORD("password");

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
