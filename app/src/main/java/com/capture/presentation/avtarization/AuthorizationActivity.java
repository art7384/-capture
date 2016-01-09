package com.capture.presentation.avtarization;

import android.os.Bundle;

import com.capture.AppSoket;
import com.capture.R;
import com.capture.presentation.common.BaseActivity;

/**
 * Created by artem on 09.01.16.
 */
public class AuthorizationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!AppSoket.getInstance().isConnect()) {
            finish();
        }
    }

}
