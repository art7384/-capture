package com.capture.presentation.connect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.capture.AppSoket;
import com.capture.R;
import com.capture.presentation.MainActivity;
import com.capture.presentation.common.BaseActivity;

/**
 * Created by artem on 06.01.16.
 */
public class ConnectActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTxtStatus;
    private Button mBtn;
    private SocetCommandReceiver mSocetCommandReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        mTxtStatus = (TextView) findViewById(R.id.activityConnect_TextView_status);
        mBtn = (Button) findViewById(R.id.activityConnect_Button_connect);
        mBtn.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        updateStatus();
    }

     /* ====== implements BaseActivity ===== */

    @Override
    protected void onConnect() {
        super.onConnect();

        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();

//        mTxtStatus.setText(R.string.socket_status_сonnect);
//        mBtn.setEnabled(true);
//        mBtn.setText(R.string.btn_ok);
    }

    @Override
    protected void onComplited(String mess){
        super.onComplited(mess);
        mTxtStatus.setText(mess);
        mBtn.setEnabled(true);
        mBtn.setText(R.string.btn_socet_connect);
    }

    @Override
    protected void onErrorConnection(String mess) {
        super.onErrorConnection(mess);
        mTxtStatus.setText(mess);
        mBtn.setEnabled(true);
        mBtn.setText(R.string.btn_socet_connect);
    }

    /* ====== implements OnClickListener ===== */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activityConnect_Button_connect: {
                if (AppSoket.getInstance().isConnect()) {
                    startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                } else {
                    AppSoket.getInstance().connectSocet();
                    onConnection();
                }
                break;
            }
        }
    }

    /* ====== FUNCTION ===== */

    public void updateStatus() {
        if (AppSoket.getInstance().isConnect()) {
            onConnect();
        } else {
            onDisconnect();
        }
    }

    public void onDisconnect() {
        mTxtStatus.setText(R.string.socket_status_noсonnect);
        mBtn.setEnabled(true);
        mBtn.setText(R.string.btn_socet_connect);
    }

    private void onConnection() {
        mTxtStatus.setText(R.string.socket_status_сonnection);
        mBtn.setEnabled(false);
        mBtn.setText(R.string.btn_socet_connect);
    }





}
