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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.eventsharing.zhewenboming.eventsharing.DatabaseHelper;
import com.eventsharing.zhewenboming.eventsharing.Models.Circle;
import com.eventsharing.zhewenboming.eventsharing.Models.Event;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
import com.eventsharing.zhewenboming.eventsharing.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends ActionBarActivity implements OnClickListener {

    private final static String USER_NAME = "username";
    private final static String USER_ID = "userID";
    private final static String Event_ID = "eventID";
    private final static String Circle_ID = "circleID";
    public static int clickedEventID;
    public static int clickedCircleID;
    private DatabaseHelper dh;
    User myUser;
    String userID;

    ListView eventListView;
    ListView circleListView;
    LinearLayout eventLinearLayout;
    LinearLayout circleLinearLayout;

    List<String> eventList;
    List<String> circleList;
    List<Integer> myFriendsID;
    List<Event> allEvents;
    List<Circle> allCircles;



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

        this.dh = new DatabaseHelper(this);

        //greeting with name
        String username = PreferenceManager.getDefaultSharedPreferences(this)
                .getString(USER_NAME, "");
        TextView helloTextView = (TextView)findViewById(R.id.helloTextView);
        myUser = this.dh.getUserByName(username);
        helloTextView.setText("Hi, " + username);
        //save my user id
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(USER_ID, myUser.getId());
        editor.commit();

        allEvents = new ArrayList<Event>();
        allCircles = new ArrayList<Circle>();
        eventList = new ArrayList<String>();
        circleList = new ArrayList<String>();


        myFriendsID =  myUser.getFriends();
        getAllEvent();
        getAllCircle();
        eventListView = (ListView) findViewById(R.id.eventListView);

        circleListView = (ListView) findViewById(R.id.circleListView);
        loadContent();
    }

    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.addEventButton:
                finish();
                startActivity(new Intent(this, EventAddActivity.class));
                break;
            case R.id.addCircleButton:
                finish();
                startActivity(new Intent(this, CircleAddActivity.class));
                break;
            case R.id.logoffButton:
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    private void getAllEvent(){
        for (Integer id : myFriendsID){
            List<Integer> userEventIds = this.dh.getEventsByUserId(id);
            for (Integer eventId : userEventIds){
                allEvents.add(this.dh.getEventById(eventId));
            }
        }
    }

    private void getAllCircle(){
        List<Integer> myCircleIDs = this.dh.getCirclesByUserId(myUser.getId());
        for (Integer id : myCircleIDs){
            allCircles.add(this.dh.getCircleById(id));
        }
    }

    private void loadContent() {
        AdapterView.OnItemClickListener eventItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedEventID = allEvents.get(position).getId();
                //clickedEvent = ((TextView)view).getText().toString();
                startActivity(new Intent(HomeActivity.this,EventActivity.class));
            }
        };

        AdapterView.OnItemClickListener circleItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //clickedEvent = ((TextView)view).getText().toString();
                clickedCircleID = allCircles.get(position).getId();
                startActivity(new Intent(HomeActivity.this,CircleActivity.class));
            }
        };
//        List<User> allUsers= this.dh.getAllUser();
//        for (User u: allUsers) {
//            String userName = u.get_userName();
//            TextView tv = new TextView(this);
//            tv.setText(userName);
//            eventLinearLayout.addView(tv);
//        }
        //eventList = Arrays.asList("Gym - Sean", "Shopping - Mom", "Running - Mike", "Snowboarding - Sam");
        for (Event e:allEvents){
            User u = this.dh.getUserById(e.getOwnerId());
            eventList.add(e.getTitle() + " - " + u.get_userName());
        }
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

        eventListView.setOnItemClickListener(eventItemClickListener);

        for (Circle c : allCircles){
            circleList.add(c.get_name());
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, circleList);
        circleListView.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
        circleListView.setOnItemClickListener(circleItemClickListener);
    }
}
