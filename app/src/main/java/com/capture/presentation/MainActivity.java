package com.capture.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.capture.R;
import com.capture.buisneslogick.service.UserService;
import com.capture.presentation.about.AboutActivity;
import com.capture.presentation.avtarization.AuthorizationActivity;
import com.capture.presentation.common.BaseActivity;
import com.capture.presentation.connect.ConnectActivity;
import com.capture.presentation.common.helper.DialogFactory;
import com.capture.presentation.profile.ProfileActivity;
import com.capture.presentation.registration.RegistrationActivity;
import com.capture.presentation.scen.CreateScenActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private DialogInterface.OnClickListener onClickDialogAuthorization = new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog, int which) {
            startActivity(new Intent(MainActivity.this, AuthorizationActivity.class));
        }
    };
    private DialogInterface.OnClickListener onClickDialogRegistration = new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog, int which) {
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
        }
    };
    private DialogInterface.OnClickListener onClickDialogCancel = new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.activityMain_FloatingActionButton_add);
        fab.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(!AppSoket.getInstance().isConnect()){
//            showDialogDisconnect(getString(R.string.socket_status_noсonnect));
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(UserService.getInstance().isAuthorization()){
            getMenuInflater().inflate(R.menu.menu_main_login, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_main_no_login, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        switch (id) {
            case R.id.action_profile: {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            case R.id.action_authorization: {
                startActivity(new Intent(this, AuthorizationActivity.class));
                return true;
            }
            case R.id.action_registration: {
                startActivity(new Intent(this, RegistrationActivity.class));
                return true;
            }
            case R.id.action_about:{
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /* ====== implements BaseActivity ===== */

    @Override
    protected void onComplited(String mess) {
        super.onComplited(mess);
        showDialogDisconnect(mess);
    }

    /* ====== implements View.OnClickListener ===== */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activityMain_FloatingActionButton_add:
                if(UserService.getInstance().isAuthorization()){
                    startActivity(new Intent(this, CreateScenActivity.class));
                } else {
                    DialogFactory.showAttention(this, getString(R.string.dialog_login_or_register),
                            getString(R.string.dialog_btn_login), onClickDialogAuthorization,
                            getString(R.string.dialog_btn_register), onClickDialogRegistration,
                            getString(R.string.dialog_btn_cancel), onClickDialogCancel);
                }

        }
    }

    /* ====== FUNCTION ===== */
    private void showDialogDisconnect(String mess) {
        DialogFactory.showError(this, mess, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, ConnectActivity.class));
                finish();
            }
        });
    }

}
