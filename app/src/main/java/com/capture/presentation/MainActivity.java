package com.capture.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.capture.AppSoket;
import com.capture.R;
import com.capture.presentation.common.BaseActivity;
import com.capture.presentation.connect.ConnectActivity;
import com.capture.presentation.common.helper.DialogFactory;
import com.capture.presentation.registration.RegistrationActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

    @Override
    protected void onResume(){
        super.onResume();
        if(!AppSoket.getInstance().isConnect()){
            showDialogDisconnect(getString(R.string.socket_status_noсonnect));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id) {
            case R.id.action_settings: {

                return true;
            }
            case R.id.action_authorization: {

                return true;
            }
            case R.id.action_registration: {
                startActivity(new Intent(this, RegistrationActivity.class));
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /* ====== implements BaseActivity ===== */

    @Override
    protected void onComplited(String mess){
        super.onComplited(mess);
        showDialogDisconnect(mess);
    }

    /* ====== FUNCTION ===== */
    private void showDialogDisconnect(String mess){
        DialogFactory.showError(this, mess, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, ConnectActivity.class));
                finish();
            }
        });
    }

}
