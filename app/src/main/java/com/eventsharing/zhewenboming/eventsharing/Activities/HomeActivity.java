package com.eventsharing.zhewenboming.eventsharing.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.eventsharing.zhewenboming.eventsharing.DatabaseHelper;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
import com.eventsharing.zhewenboming.eventsharing.R;

import java.util.List;

public class HomeActivity extends ActionBarActivity {

    private DatabaseHelper dh;
    TextView eventText;
    LinearLayout eventLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        eventLinearLayout = (LinearLayout) findViewById(R.id.eventLinearLayout);
        eventText = (TextView) findViewById(R.id.eventTextVIew);
        loadEventText();
    }

    private void loadEventText() {
//        this.dh = DatabaseHelper.getInstance(this);
//        for(int i = 0; i< 100; i++) {
//            String userName = "user" + String.valueOf(i);
//            this.dh.insertUser(userName, "1");
//        }
//
//        List<User> allUsers= this.dh.getAllUser();
//        for (User u: allUsers) {
//            String userName = u.get_userName();
//            TextView tv = new TextView(HomeActivity.this);
//            tv.setText(userName);
//            eventLinearLayout.addView(tv);
//        }
//
//        for(int i = 0; i< 100; i++) {
//            String s = "Event " + String.valueOf(i);
//            TextView tv = new TextView(HomeActivity.this);
//            tv.setText(s);
//            eventLinearLayout.addView(tv);
//        }

        //eventText.setMovementMethod(new ScrollingMovementMethod());
        //eventText.setText(s);
    }
}
