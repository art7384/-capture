package com.capture.model;

/**
 * Created by artem on 27.01.16.
 */
public class TakeoverModel extends BaseModel {

    public long radius = 0;
    public long speed = 0;
    public long time = 0;
    public long maxRadius = 0;

    @Override
    public ModelType getModelType() {
        return ModelType.TAKOVER;
    }

    public enum JsonKey {
        RADIUS("radius"),
        SPEED("speed"),
        TIME("time"),
        MAX_RADIUS("maxRadius");

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
