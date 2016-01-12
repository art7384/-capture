package com.capture.constant;

/**
 * Created by artem on 06.01.16.
 */
public enum ObjectRole {
    SCEN("scen"),
    USER("user");
    String role;

    ObjectRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    static public ObjectRole get(String role) {
        if (role.equals(ObjectRole.USER.toString()))
            return ObjectRole.USER;
        if (role.equals(ObjectRole.SCEN.toString()))
            return ObjectRole.SCEN;
        return null;
    }
}
