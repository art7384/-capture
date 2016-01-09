package com.capture.presentation.about;

import android.os.Bundle;
import android.widget.TextView;

import com.capture.AppSoket;
import com.capture.R;
import com.capture.presentation.common.BaseActivity;

/**
 * Created by artem on 09.01.16.
 */
public class AboutActivity extends BaseActivity {

    private TextView mTxtVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTxtVersion = (TextView) findViewById(R.id.activityAbout_TextView_version);
        mTxtVersion.setText(AppSoket.getInstance().getPackageInfo().versionName
                + "("
                + AppSoket.getInstance().getPackageInfo().versionCode
                + ")");
    }
}
