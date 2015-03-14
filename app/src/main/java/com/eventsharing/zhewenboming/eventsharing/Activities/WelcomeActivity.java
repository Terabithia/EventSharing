package com.eventsharing.zhewenboming.eventsharing.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.eventsharing.zhewenboming.eventsharing.R;

/**
 * Created by Terabithia on 2015/3/9.
 *
 * Welcome page should provide following options for user to choose: browse circles, browse events, edit settings, log off. more to be added
 *
 *
 */
public class WelcomeActivity extends Activity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //four buttons, each for one option
        View btnEvents = (Button) findViewById(R.id.show_events_button);
        btnEvents.setOnClickListener(this);
        View btnCircles = (Button) findViewById(R.id.show_circles_button);
        btnCircles.setOnClickListener(this);
        View btnSetting = (Button) findViewById(R.id.setting_button);
        btnSetting.setOnClickListener(this);
        View btnLogOff = (Button) findViewById(R.id.log_off_button);
        btnLogOff.setOnClickListener(this);
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Login", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Login", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Login", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Login", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Login", "onDestroy");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_events_button:
                startActivity(new Intent(this, EventActivity.class)); // to be implemented
                break;
            case R.id.show_circles_button:
                startActivity(new Intent(this, CircleActivity.class)); // to be implemented
                break;
            case R.id.setting_button:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.log_off_button:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

}
