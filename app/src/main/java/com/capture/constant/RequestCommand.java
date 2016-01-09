package com.capture.constant;

/**
 * Created by artem on 09.01.16.
 */
public enum RequestCommand {
    EXIT("exit"),
    CREATE_SCEN("create_scen"),
    CONNECT_SCEN("connect_scen"),
    GET_SCEN("get_scen"),
    CREATE_USER("create_user"),
    AUTHORIZATION("authorization");
    private String command;
    RequestCommand(String command){
        this.command = command;
    }

    @Override
    public String toString(){
        return command;
    }

}
