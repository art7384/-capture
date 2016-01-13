package com.capture.presentation.profile;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.capture.AppSoket;
import com.capture.R;
import com.capture.object.ReturnObject;
import com.capture.object.UserObject;
import com.capture.buisneslogick.service.UserService;
import com.capture.buisneslogick.service.helpers.OnCompliteListern;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;
import com.capture.presentation.common.BaseActivity;
import com.capture.presentation.common.helper.DialogFactory;

import org.json.JSONException;

/**
 * Created by artem on 09.01.16.
 */
public class ProfileActivity extends BaseActivity {

    private TextView txtEmail;
    private TextView txtNicname;

    private OnCompliteListern mOnCompliteExitListern = new OnCompliteListern() {
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
            int status = returnObject.getReturnModul().getStatus();
            String info = returnObject.getReturnModul().getText();
            DialogFactory.showError(ProfileActivity.this, status + ": " + info);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtEmail = (TextView) findViewById(R.id.activityProfile_TextView_email);
        txtNicname = (TextView) findViewById(R.id.activityProfile_TextView_nicname);

        UserObject user = UserService.getInstance().getUserObject();
        txtEmail.setText(user.getUserModul().getEmail());
        txtNicname.setText(user.getGeneralModul().getNameObject());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!AppSoket.getInstance().isConnect()) {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_exit: {
                exit();
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void exit(){
        showProgressDialog();
        try {
            UserService.getInstance().exit(mOnCompliteExitListern, mOnErrorTransportListner);
        } catch (JSONException e) {
            e.printStackTrace();
            DialogFactory.showError(this, e.getMessage());
        }
    }

}
