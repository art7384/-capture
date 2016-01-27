package com.capture.model;

/**
 * Created by artem on 26.01.16.
 */
public abstract class BaseModel {

    public enum ModelType {
        USER("user"),
        RETURN("return"),
        REQUEST("request"),
        GENERAL("general"),
        VISIBLE("visible"),
        TAKOVER("takover"),
        MESSAGE("message"),
        REPOSITORY("repository"),
        INDEX("index"),
        CLIENT("client");

        String type;

        ModelType(String type) {
            this.type = type;
        }

        @Override
        public String toString(){
            return type;
        }

    }

    abstract public ModelType getModelType();

}
