package com.capture.presentation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import com.capture.App;
import com.capture.R;
import com.capture.buisneslogick.service.RequestClientService;
import com.capture.service.SocketService;

import org.json.JSONException;

/**
 * Created by artem on 29.12.15.
 */
public class SplashActivity extends Activity {

    private TextView mTextStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mTextStatus = (TextView) findViewById(R.id.activitySplash_TextView_status);

        mTextStatus.setText(getString(R.string.сonnection));

        try {
            RequestClientService.getInstance().isСurrent();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
