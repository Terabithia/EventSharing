package com.eventsharing.zhewenboming.eventsharing.Activities;

import android.content.DialogInterface;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.eventsharing.zhewenboming.eventsharing.DatabaseHelper;
import com.eventsharing.zhewenboming.eventsharing.Models.Circle;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
import com.eventsharing.zhewenboming.eventsharing.R;

import org.w3c.dom.Text;

import java.util.List;

public class CircleActivity extends ActionBarActivity implements OnClickListener{

    User myUser;
    User addedUser;
    List<Integer> userList;
    private EditText userEditText;
    LinearLayout userListLayout;
    Circle thisCircle;
    private final static String USER_ID = "userID";
    private DatabaseHelper dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        this.dh = new DatabaseHelper(this);
        int myID = PreferenceManager.getDefaultSharedPreferences(this)
                .getInt(USER_ID, 0);
        myUser = this.dh.getUserById(myID);
        int thisCircleId = HomeActivity.clickedCircleID;
        thisCircle = this.dh.getCircleById(thisCircleId);
        String circleNameString = thisCircle.get_name();
        TextView circleNameTextView = (TextView) findViewById(R.id.circleNameViewLabel);
        circleNameTextView.setText(circleNameString);
        userList = this.dh.getUsersByCircleId(thisCircleId);
        userEditText = (EditText) findViewById(R.id.circleAddUserTextEdit);

        userListLayout = (LinearLayout) findViewById(R.id.userListLayout);
        for(Integer userId : userList){
            User thisUser = this.dh.getUserById(userId);
            String userName = thisUser.get_userName();
            TextView tv = new TextView(this);
            tv.setText(userName);
            userListLayout.addView(tv);
        }

        Button circleAddUserBtn = (Button) findViewById(R.id.circleAddUserBtn);
        circleAddUserBtn.setOnClickListener(this);
        Button circleAddUserCancelBtn = (Button) findViewById((R.id.circleAddUserCancelBtn));
        circleAddUserCancelBtn.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.circleAddUserBtn:
                addUser();
                finish();
                break;
            case R.id.circleAddUserCancelBtn:
                finish();
                break;
        }
    }

    private void addUser(){
        //userList.add(addedUser.getId());
        addedUser = this.dh.getUserByName(userEditText.getText().toString());
        this.dh.addUserToCircle(thisCircle.getId(),addedUser.getId());
        List<Integer> friendList = myUser.getFriends();
        if (!friendList.contains(addedUser.getId())){
            friendList.add(addedUser.getId());
        }
        this.dh.addFriendById(myUser.getId(), addedUser.getId());
    }
}
