package com.eventsharing.zhewenboming.eventsharing.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.eventsharing.zhewenboming.eventsharing.DatabaseHelper;
import com.eventsharing.zhewenboming.eventsharing.Models.Event;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
import com.eventsharing.zhewenboming.eventsharing.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends ActionBarActivity implements View.OnClickListener {

    private final static String USER_NAME = "username";
    private final static String USER_ID = "userID";
    private DatabaseHelper dh;
    LinearLayout eventLinearLayout;
    ListView eventListView;
    ListView circleListView;
    LinearLayout circleLinearLayout;
    List<String> eventList;
    List<String> circleList;

    public static String clickedEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        View addEvenButton = findViewById(R.id.addEventButton);
        addEvenButton.setOnClickListener(this);
        View addCircleButton = findViewById(R.id.addCircleButton);
        addCircleButton.setOnClickListener(this);
        View logoffButton = findViewById(R.id.logoffButton);
        logoffButton.setOnClickListener(this);

        String username = PreferenceManager.getDefaultSharedPreferences(this)
                .getString(USER_NAME, "");
        TextView helloTextView = (TextView)findViewById(R.id.helloTextView);
        helloTextView.setText("Hi, " + username);

        eventListView = (ListView) findViewById(R.id.eventListView);
        circleListView = (ListView) findViewById(R.id.circleListView);
        loadContent();
    }

    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.addEventButton:
                startActivity(new Intent(this, EventAddActivity.class));
                break;
            case R.id.addCircleButton:
                startActivity(new Intent(this, CircleAddActivity.class));
                break;
            case R.id.logoffButton:
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
    private void loadContent() {
        this.dh = new DatabaseHelper(this);





//        for(int i = 0; i< 100; i++) {
//            String userName = "user" + String.valueOf(i);
//            this.dh.insertUser(userName, "1");
//            List<String> allUsers= this.dh.selectAllUsers(userName, "1");
//            if(allUsers.size()>0){
//                TextView tv = new TextView(HomeActivity.this);
//                tv.setText(userName);
//            }
//        }

//        List<User> allUsers= this.dh.getAllUser();
//        for (User u: allUsers) {
//            String userName = u.get_userName();
//            TextView tv = new TextView(this);
//            tv.setText(userName);
//            eventLinearLayout.addView(tv);
//        }
        eventList = Arrays.asList("Shopping - Mom");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventList);
        eventListView.setAdapter(adapter);
//        for(int i = 0; i< 20; i++) {
//            final String s = "Event " + String.valueOf(i);
//            String userName = "user" + String.valueOf(i);
//            this.dh.insertUser(userName, "1");
//            List<String> allUsers= this.dh.selectAllUsers(userName, "1");
//            if(allUsers.size()>0){
//                //eventList.add(allUsers.get(0));
//            }
//            eventList.add(s);
////            TextView tv = new TextView(this);
////            tv.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    clickedEvent = s;
////                    startActivity(new Intent(HomeActivity.this,EventActivity.class));
////                }
////            });
////            tv.setText(s);
//            //eventLinearLayout.addView(tv);
//            //eventListView.addFooterView(tv);
//        }
        adapter.notifyDataSetChanged();
        adapter.add("Test!");
        AdapterView.OnItemClickListener eventItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedEvent = ((TextView)view).getText().toString();
                startActivity(new Intent(HomeActivity.this,EventActivity.class));
            }
        };

        AdapterView.OnItemClickListener circleItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedEvent = ((TextView)view).getText().toString();
                startActivity(new Intent(HomeActivity.this,CircleActivity.class));
            }
        };
        eventListView.setOnItemClickListener(eventItemClickListener);

        circleList = Arrays.asList("Friends", "Family", "co-Worker", "School", "Special");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, circleList);
        circleListView.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
        circleListView.setOnItemClickListener(circleItemClickListener);


//        for(int i = 0; i< 20; i++) {
//            String s = "Circle " + String.valueOf(i);
//            TextView tv = new TextView(this);
//            tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(HomeActivity.this,CircleActivity.class));
//                }
//            });
//            tv.setText(s);
//            circleLinearLayout.addView(tv);
//        }
    }
}
