package com.capture.constant;

/**
 * Created by artem on 06.01.16.
 */
public enum ObjectRole {
    SCEN("scen"),
    USER("user");
    String role;
    ObjectRole(String role){
        this.role = role;
    }
    @Override
    public String toString(){
        return role;
    }
}
