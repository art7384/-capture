package com.capture.presentation.menu.registration;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.capture.AppSoket;
import com.capture.R;
import com.capture.buisneslogick.object.RequestServerObject;
import com.capture.buisneslogick.object.UserObject;
import com.capture.buisneslogick.service.UserService;
import com.capture.buisneslogick.service.helpers.OnCompliteListern;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;
import com.capture.model.UserModel;
import com.capture.presentation.common.BaseActivity;
import com.capture.presentation.helper.DialogFactory;

import org.json.JSONException;

import java.security.NoSuchAlgorithmException;

/**
 * Created by artem on 06.01.16.
 */
public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEdEmail;
    private EditText mEdNick;
    private EditText mEdPass;
    private EditText mEdPass2;
    private Button mBtOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mEdEmail = (EditText) findViewById(R.id.activityRegistration_EditText_email);
        mEdNick = (EditText) findViewById(R.id.activityRegistration_EditText_nick);
        mEdPass = (EditText) findViewById(R.id.activityRegistration_EditText_pass);
        mEdPass2 = (EditText) findViewById(R.id.activityRegistration_EditText_pass2);
        mBtOk = (Button) findViewById(R.id.activityRegistration_Button_ok);

        mBtOk.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!AppSoket.getInstance().isConnect()) {
            finish();
        }
    }

    /* ====== implements BaseActivity ===== */

    @Override
    protected void onComplited(String mess) {
        super.onComplited(mess);
        showDialogDisconnect(mess);
    }

    /* ====== FUNCTION ===== */
    private void showDialogDisconnect(String mess) {
        DialogFactory.showError(this, mess, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
    }

    private boolean isValid(){
        if(mEdEmail.getText().length() < 3){
            mEdEmail.setError("Invalid");
            return false;
        }
        if(mEdNick.getText().length() < 3){
            mEdNick.setError("Invalid");
            return false;
        }
        if(mEdPass.getText().length() < 3){
            mEdPass.setError("Invalid");
            return false;
        }
        if(!mEdPass.getText().toString().equals(mEdPass2.getText().toString())){
            mEdPass2.setError("Invalid");
            return false;
        }
        return true;
    }

    private void registration(){
        UserModel user = new UserModel();
        user.password = mEdPass.getText().toString();
        user.email = mEdEmail.getText().toString();
        String nick = mEdNick.getText().toString();
        try {
            showDialogDisconnect(getString(R.string.title_registration));
            UserService.getInstance().registration(user, nick, new OnCompliteListern() {
                @Override
                public void onComplite() {
                    cancelProgressDialog();
                    if(RegistrationActivity.this == null) return;

                }
            }, new OnErrorTransportListner() {
                @Override
                public void onError(RequestServerObject requestServerObject) {
                    cancelProgressDialog();
                    if(RegistrationActivity.this == null) return;
                    int staus = requestServerObject.getRequestModul().getStatus();
                    String txt = requestServerObject.getRequestModul().getText();
                    switch (staus){
                        case 403: {
                            DialogFactory.showError(RegistrationActivity.this, getString(R.string.dialog_email_exists));
                            break;
                        }
                        case 409: {
                            DialogFactory.showError(RegistrationActivity.this, getString(R.string.dialog_nickname_is_not_available));
                            break;
                        }
                        default: {
                            DialogFactory.showError(RegistrationActivity.this, "" + staus + ": " + txt);
                            break;
                        }
                    }
                }
            });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            mEdPass.setError("Invalid");
            DialogFactory.showError(this, e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            DialogFactory.showError(this, e.getMessage());
        }
    }

    /* ====== implements OnClickListener ===== */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activityRegistration_Button_ok: {

                if(isValid()){
                    registration();
                }

                break;
            }
        }
    }
}
