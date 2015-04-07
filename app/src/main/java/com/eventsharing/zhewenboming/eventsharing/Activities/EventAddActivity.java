package com.eventsharing.zhewenboming.eventsharing.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eventsharing.zhewenboming.eventsharing.DatabaseHelper;
import com.eventsharing.zhewenboming.eventsharing.Models.Circle;
import com.eventsharing.zhewenboming.eventsharing.Models.Event;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
import com.eventsharing.zhewenboming.eventsharing.R;

import java.util.List;

public class EventAddActivity extends ActionBarActivity implements OnClickListener {
    User myUser;
    List<Integer> circleIDs;
    private final static String USER_ID = "userID";
    private DatabaseHelper dh;
    private EditText eventAddTitleEdit;
    private EditText eventAddDesEdit;
    private Event newEvent;
    List<CheckBox> checkBoxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
        this.dh = new DatabaseHelper(this);
        int myID = PreferenceManager.getDefaultSharedPreferences(this)
                .getInt(USER_ID, 0);
        myUser = this.dh.getUserById(myID);
        eventAddTitleEdit = (EditText) findViewById(R.id.eventTitleEditText);
        eventAddDesEdit = (EditText) findViewById(R.id.eventDesEditText);
        View btnEventAdd = (Button) findViewById(R.id.addEventSaveBtn);
        btnEventAdd.setOnClickListener(this);
        View btnEventCancel = (Button) findViewById(R.id.addEventCancelBtn);
        btnEventCancel.setOnClickListener(this);
        LinearLayout circleListLayout = (LinearLayout) findViewById(R.id.eventCircleListLayout);
        circleIDs = this.dh.getCirclesByUserId(myUser.getId());
        for (Integer id : circleIDs){
            CheckBox cb = new CheckBox(getApplicationContext());
            Circle c = this.dh.getCircleById(id);
            cb.setText(c.get_name());
            cb.setTextColor(Color.GRAY);
            circleListLayout.addView(cb);
            checkBoxList.add(cb);
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.addEventSaveBtn:

                addEvent();
                finish();
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.addEventCancelBtn:
                finish();
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
    }

    private void addEvent(){
        String eventTitle = eventAddTitleEdit.getText().toString();
        String eventDes = eventAddDesEdit.getText().toString();

        this.dh.insertEvent(eventTitle, eventDes, myUser.getId(), "location");
        //newEvent = new Event(eventTitle,eventDes,myUser.getId(),"location");
        //eventIDs = myUser.getEvents();
        //eventIDs.add(newEvent.getId());
        //myUser.setEvents(eventIDs);
    }
}
