package com.eventsharing.zhewenboming.eventsharing.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eventsharing.zhewenboming.eventsharing.DatabaseHelper;
import com.eventsharing.zhewenboming.eventsharing.Models.Circle;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
import com.eventsharing.zhewenboming.eventsharing.R;

import java.util.List;

public class CircleAddActivity extends ActionBarActivity implements OnClickListener {

    User myUser;
    List<Integer> circleIDs;
    private final static String USER_ID = "userID";
    private DatabaseHelper dh;
    private EditText circleAddNameEdit;
    private Circle newCircle;
    private List<Integer> myCircles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_add);
        this.dh = new DatabaseHelper(this);
        int myID = PreferenceManager.getDefaultSharedPreferences(this)
                .getInt(USER_ID, 0);
        myUser = this.dh.getUserById(myID);
        circleAddNameEdit = (EditText) findViewById(R.id.circleTitleEditText);
        View btnCircleAdd = (Button) findViewById(R.id.addCircleSaveBtn);
        btnCircleAdd.setOnClickListener(this);
        View btnCircleCancel = (Button) findViewById(R.id.addCircleCancelBtn);
        btnCircleCancel.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.addCircleSaveBtn:
                addCircle();
                finish();
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.addCircleCancelBtn:
                finish();
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
    }

    private void addCircle(){
        String circleName = circleAddNameEdit.getText().toString();

        this.dh.insertCircle(circleName, myUser.getId());
//        newCircle = new Circle(CircleName);
//        myCircles = myUser.getCircle();
//        myCircles.add(newCircle.getId());
//        myUser.setCircle(myCircles);
    }
}
