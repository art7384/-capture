package com.capture.presentation.avtarization;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.capture.AppSoket;
import com.capture.R;
import com.capture.buisneslogick.helpers.Md5Convector;
import com.capture.buisneslogick.service.UserService;
import com.capture.buisneslogick.transport.OnCompliteListner;
import com.capture.buisneslogick.transport.OnErrorTransportListner;
import com.capture.model.UserModel;
import com.capture.object.ReturnObject;
import com.capture.presentation.common.BaseActivity;
import com.capture.presentation.common.helper.DialogFactory;

import org.json.JSONException;

import java.security.NoSuchAlgorithmException;

/**
 * Created by artem on 09.01.16.
 */
public class AuthorizationActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEdEmail;
    private EditText mEdPass;
    private Button mBtOk;
    private Handler mHandler = new Handler();

    private OnCompliteListner mOnCompliteListner = new OnCompliteListner() {
        @Override
        public void onComplite() {
            cancelProgressDialog();
            finish();
        }
    };

    private OnErrorTransportListner mOnErrorTransportListner = new OnErrorTransportListner() {
        @Override
        public void onError(ReturnObject returnObject) {
            cancelProgressDialog();
            if (AuthorizationActivity.this == null) return;
            final int staus = returnObject.getReturnModel().status;
            final String txt = returnObject.getReturnModel().text;
            switch (staus) {
                case 404: {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            DialogFactory.showError(AuthorizationActivity.this, getString(R.string.dialog_login_error));
                        }
                    });

                    break;
                }
                default: {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            DialogFactory.showError(AuthorizationActivity.this, "" + staus + ": " + txt);
                        }
                    });

                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEdEmail = (EditText) findViewById(R.id.activityAuthorization_EditText_email);
        mEdPass = (EditText) findViewById(R.id.activityAuthorization_EditText_pass);
        mBtOk = (Button) findViewById(R.id.activityAuthorization_EditText_ok);

        mBtOk.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!AppSoket.getInstance().isConnect()) {
            finish();
        }
    }

    /* ====== implements View.OnClickListener ===== */

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activityAuthorization_EditText_ok:
                if (isValid()) {
                    avtorization();
                }
                break;
        }
    }

    /* ====== FUNCTION ===== */
    private boolean isValid() {
        if (mEdEmail.getText().length() < 3) {
            mEdEmail.setError(getString(R.string.ed_invalid));
            return false;
        }
        if (mEdPass.getText().length() < 3) {
            mEdPass.setError(getString(R.string.ed_invalid));
            return false;
        }
        return true;
    }

    private void avtorization() {
        UserModel user = null;
        try {
            user = createUserModel();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(user == null){
            mEdPass.setError(getString(R.string.ed_invalid));
            return;
        }
        try {
            showProgressDialog(getString(R.string.title_authorization));
            UserService.getInstance().authorization(user, mOnCompliteListner, mOnErrorTransportListner);
        } catch (JSONException e) {
            e.printStackTrace();
            DialogFactory.showError(this, e.getMessage());
        }
    }

    /* ====== HELPERS ===== */

    private UserModel createUserModel() throws NoSuchAlgorithmException {
        UserModel user = new UserModel();
        user.password = Md5Convector.md5(mEdPass.getText().toString());
        user.email = mEdEmail.getText().toString();
        return user;
    }

}
