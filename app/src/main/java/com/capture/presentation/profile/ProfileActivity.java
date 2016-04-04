package com.capture.presentation.profile;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.capture.AppSoketTest;
import com.capture.R;
import com.capture.buisneslogick.persisten.UserProfile;
import com.capture.object.UserObject;
import com.capture.buisneslogick.service.UserService;
import com.capture.presentation.common.BaseActivity;

/**
 * Created by artem on 09.01.16.
 */
public class ProfileActivity extends BaseActivity {

    private TextView txtEmail;
    private TextView txtNicname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtEmail = (TextView) findViewById(R.id.activityProfile_TextView_email);
        txtNicname = (TextView) findViewById(R.id.activityProfile_TextView_nicname);

        UserObject user = UserService.getInstance().getUserObject();
        txtEmail.setText(user.getUserModel().email);
        txtNicname.setText(user.getGeneralModel().nameObject);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!AppSoketTest.getInstance().isConnect()) {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_exit: {
                exit();
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void exit(){
        UserProfile.getInstance().exit();
        AppSoketTest.getInstance().disconnect();
        finish();
    }

}
