package com.capture.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.capture.App;
import com.capture.R;
import com.capture.service.SocketService;

/**
 * Created by artem on 29.12.15.
 */
public class SplashActivity extends Activity implements App.OnSocetServiceListner {

    private TextView mTextStatus;
    private SocketService mSocketService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mTextStatus = (TextView) findViewById(R.id.activitySplash_TextView_status);

        mTextStatus.setText(getString(R.string.сonnection));


        mSocketService = App.getInstance().setOnSocetServiceListner(this);

    }

    // OnSocetServiceListner

    @Override
    public void onServiceConnected(SocketService socketService) {
        mSocketService = socketService;
    }

    @Override
    public void onServiceDisconnected() {

    }
    // End OnSocetServiceListner
}
