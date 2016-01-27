package com.capture.presentation.registration;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.capture.AppSoket;
import com.capture.R;
import com.capture.buisneslogick.service.UserService;
import com.capture.buisneslogick.transport.OnErrorTransportListner;
import com.capture.model.UserModel;
import com.capture.object.ReturnObject;
import com.capture.presentation.common.BaseActivity;
import com.capture.presentation.common.helper.DialogFactory;
import com.capture.buisneslogick.transport.OnCompliteListner;

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

    private OnCompliteListner mOnCompliteListner = new OnCompliteListner() {
        @Override
        public void onComplite() {
            cancelProgressDialog();
            if (RegistrationActivity.this == null) return;

        }
    };

    private OnErrorTransportListner mOnErrorTransportListner = new OnErrorTransportListner() {
        @Override
        public void onError(ReturnObject returnObject) {
            cancelProgressDialog();
            if (RegistrationActivity.this == null) return;
            int staus = returnObject.getReturnModel().status;
            String txt = returnObject.getReturnModel().text;
            switch (staus) {
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
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    /* ====== HELPERS ===== */

    private UserModel createUserModel() {
        UserModel user = new UserModel();
        user.password = mEdPass.getText().toString();
        user.email = mEdEmail.getText().toString();
        String nick = mEdNick.getText().toString();
        return user;
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

    private boolean isValid() {
        if (mEdEmail.getText().length() < 3) {
            mEdEmail.setError(getString(R.string.ed_invalid));
            return false;
        }
        if (mEdNick.getText().length() < 3) {
            mEdNick.setError(getString(R.string.ed_invalid));
            return false;
        }
        if (mEdPass.getText().length() < 3) {
            mEdPass.setError(getString(R.string.ed_invalid));
            return false;
        }
        if (!mEdPass.getText().toString().equals(mEdPass2.getText().toString())) {
            mEdPass2.setError(getString(R.string.ed_invalid));
            return false;
        }
        return true;
    }

    private void registration() {
        UserModel user = createUserModel();
        String nick = mEdNick.getText().toString();
        try {
            showProgressDialog(getString(R.string.title_registration));
            UserService.getInstance().registration(user, nick, mOnCompliteListner, mOnErrorTransportListner);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            mEdPass.setError(getString(R.string.ed_invalid));
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

                if (isValid()) {
                    registration();
                }

                break;
            }
        }
    }
}
