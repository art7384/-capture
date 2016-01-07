package com.capture.role;

/**
 * Created by artem on 06.01.16.
 */
public enum Role {
    USER("user");
    String role;
    Role(String role){
        this.role = role;
    }
    @Override
    public String toString(){
        return role;
    }
}
