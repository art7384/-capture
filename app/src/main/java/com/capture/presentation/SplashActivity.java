package com.capture.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.capture.R;

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

    }

}
