package com.capture.presentation.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.capture.AppSoket;
import com.capture.R;
import com.capture.buisneslogick.object.requestserver.RequestServerObject;
import com.capture.buisneslogick.service.UserService;
import com.capture.buisneslogick.service.helpers.OnCompliteListern;
import com.capture.buisneslogick.transport.helper.OnErrorTransportListner;
import com.capture.presentation.avtarization.AuthorizationActivity;
import com.capture.presentation.common.BaseActivity;
import com.capture.presentation.common.helper.DialogFactory;
import com.capture.presentation.registration.RegistrationActivity;

import org.json.JSONException;

/**
 * Created by artem on 09.01.16.
 */
public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                showProgressDialog();
                try {
                    UserService.getInstance().exit(
                            new OnCompliteListern() {
                                @Override
                                public void onComplite() {
                                    cancelProgressDialog();
                                    finish();
                                }
                            },
                            new OnErrorTransportListner() {
                                @Override
                                public void onError(RequestServerObject requestServerObject) {
                                    cancelProgressDialog();
                                    int status = requestServerObject.getRequestModul().getStatus();
                                    String info = requestServerObject.getRequestModul().getText();
                                    DialogFactory.showError(ProfileActivity.this, status + ": " + info);
                                }
                            }
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                    DialogFactory.showError(this, e.getMessage());
                }
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }

}
