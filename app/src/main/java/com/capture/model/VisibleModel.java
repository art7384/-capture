package com.capture.model;

/**
 * Created by artem on 27.01.16.
 */
public class VisibleModel extends BaseModel {
    public long radius = 0;
    public long lat = 0;
    public long lng = 0;

    @Override
    public ModelType getModelType() {
        return ModelType.VISIBLE;
    }

    public enum JsonKey {
        RADIUS("radius"),
        LAT("lat"),
        LNG("lng");

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
