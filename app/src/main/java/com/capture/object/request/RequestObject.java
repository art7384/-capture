package com.capture.object.request;

import com.capture.buisneslogick.convector.parser.ParserRequest;
import com.capture.model.BaseModel;
import com.capture.model.RequestModel;
import com.capture.object.common.BaseObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 09.01.16.
 */
public class RequestObject extends BaseObject {

    private RequestModel requestModel = new RequestModel();

    public RequestObject(){

    }

    public RequestObject(JSONObject jsonObject){
        try {
            JSONObject jsRequest = jsonObject.getJSONObject(requestModel.getModelType().toString());
            requestModel = ParserRequest.pars(jsRequest);
        } catch (JSONException e) {
            e.printStackTrace();
            requestModel = null;
        }
    }

    public RequestModel getRequestModel() {
        return requestModel;
    }

    public void setRequestModel(RequestModel requestModel) {
        this.requestModel = requestModel;
    }

    @Override
    public BaseModel[] getModels() {
        return new BaseModel[]{requestModel};
    }


    public enum Command {
        UNAUTHORIZED("unauthorized"),
        EXIT("exit"),
        CREATE_SCEN("create_scen"),
        CONNECT_SCEN("connect_scen"),
        GET_SCEN("get_scen"),
        CREATE_USER("create_user"),
        AUTHORIZATION("authorization");
        private String command;

        Command(String command) {
            this.command = command;
        }

        @Override
        public String toString() {
            return command;
        }

        static public Command get(String command){
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
            } else if (command.equals(UNAUTHORIZED.toString())){
                return UNAUTHORIZED;
            }
            return null;
        }

    }

}
