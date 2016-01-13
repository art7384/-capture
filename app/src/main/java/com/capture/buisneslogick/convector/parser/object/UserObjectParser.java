package com.capture.buisneslogick.convector.parser.object;

import com.capture.buisneslogick.convector.parser.models.ParserGeneral;
import com.capture.buisneslogick.convector.parser.models.ParserUser;
import com.capture.object.UserObject;
import com.capture.model.GeneralModel;
import com.capture.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by artem on 09.01.16.
 */
public class UserObjectParser {
    static public UserObject pars(JSONObject jsObj) throws JSONException {
/*{
            "general":{
                "id":15,
                "name":"qwecxz",
                "role":"user"
            },
            "user":{
                "email":"qwecfr@gf.gt",
                "token":"345a7c1da6ff89eb4da7a651528a4546"
            }
        }*/
        UserObject user = new UserObject();

        JSONObject jsGeneral = jsObj.getJSONObject(user.getGeneralModul().getJsonKey());
        GeneralModel generalModel = ParserGeneral.pars(jsGeneral);
        user.setGeneralModel(generalModel);

        JSONObject jsUser = jsObj.getJSONObject(user.getUserModul().getJsonKey());
        UserModel userModel = ParserUser.pars(jsUser);
        user.setUserModel(userModel);

        return user;
    }


}
