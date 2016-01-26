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

    RequestCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

    static public RequestCommand get(String command){
        if (command.equals(EXIT.toString())){
            return EXIT;
        }else if (command.equals(CREATE_SCEN.toString())){
            return CREATE_SCEN;
        } else if (command.equals(CONNECT_SCEN.toString())){
            return CONNECT_SCEN;
        } else if (command.equals(GET_SCEN.toString())){
            return GET_SCEN;
        } else if (command.equals(CREATE_USER.toString())){
            return CREATE_USER;
        } else if (command.equals(AUTHORIZATION.toString())){
            return AUTHORIZATION;
        }
        return null;
    }

}
