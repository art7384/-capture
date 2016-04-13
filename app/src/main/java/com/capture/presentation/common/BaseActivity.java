package com.capture.presentation.common;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.capture.AppSoket;

/**
 * Created by artem on 06.01.16.
 */
public class BaseActivity extends AppCompatActivity {

    private SocetCommandReceiver mReceiver = new SocetCommandReceiver();
    private ProgressDialog progressDialog = null;

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, new IntentFilter(AppSoket.KeyBroadcast.SOCET_UPDATE));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    protected void onConnect() {

    }

    protected void onComplited(String message) {

    }

    protected void onErrorConnection(String message) {

    }

    protected void showProgressDialog() {
        showProgressDialog(null);
    }

    protected void showProgressDialog(String mess) {
        cancelProgressDialog();
        progressDialog = new ProgressDialog(this);
        if (mess != null) progressDialog.setMessage(mess);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    protected void cancelProgressDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        progressDialog = null;
    }


    public class SocetCommandReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            int event = intent.getIntExtra(AppSoket.KeyExtra.COMMAND.toString(), -1);
            if (event == AppSoket.KeyEvent.CONNECT) {
                onConnect();
            } else if (event == AppSoket.KeyEvent.COMPLITED) {
                String mess = intent.getStringExtra(AppSoket.KeyExtra.MESSAGE.toString());
                onErrorConnection(mess);
            } else if (event == AppSoket.KeyEvent.ERROR_CONNECT) {
                String mess = intent.getStringExtra(AppSoket.KeyExtra.MESSAGE.toString());
                onErrorConnection(mess);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
