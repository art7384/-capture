package com.capture.presentation.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.capture.R;
import com.capture.presentation.MainActivity;
import com.capture.presentation.connect.ConnectActivity;
import com.capture.presentation.game.GameActivity;

/**
 * Created by artem on 29.12.15.
 */
public class SplashActivity extends Activity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler.postAtTime(new Runnable() {
            @Override
            public void run() {
                //startActivity(new Intent(SplashActivity.this, ConnectActivity.class));
                startActivity(new Intent(SplashActivity.this, GameActivity.class));
                        finish();
            }
        }, 3000);

    }

}
