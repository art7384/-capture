package com.capture.model;

/**
 * Created by artem on 27.01.16.
 */
public class RepositoryModel extends BaseModel {

    public int banner = 0;
    public int maxBanner = 0;
    public boolean isPlant = false;

    @Override
    public ModelType getModelType() {
        return ModelType.REPOSITORY;
    }

    public enum JsonKey {
        BANNER("banner"),
        MAX_BANNER("maxBanner"),
        IS_PLANT("isPlant");

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
