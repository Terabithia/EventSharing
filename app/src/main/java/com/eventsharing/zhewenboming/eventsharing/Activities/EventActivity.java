package com.eventsharing.zhewenboming.eventsharing.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eventsharing.zhewenboming.eventsharing.DatabaseHelper;
import com.eventsharing.zhewenboming.eventsharing.Models.Comment;
import com.eventsharing.zhewenboming.eventsharing.Models.Event;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
import com.eventsharing.zhewenboming.eventsharing.R;

import org.w3c.dom.Text;

import java.util.List;

public class EventActivity extends ActionBarActivity implements OnClickListener {

    private DatabaseHelper dh;
    int clickEventID;
    Event thisEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        this.dh = new DatabaseHelper(this);
        LinearLayout eventLayout = (LinearLayout) findViewById(R.id.eventPageLinearLayout);
        clickEventID = HomeActivity.clickedEventID;
        thisEvent = this.dh.getEventById(clickEventID);
        Button eventComBtn = (Button) findViewById(R.id.eventComBtn);
        eventComBtn.setOnClickListener(this);
        Button eventComCancelBtn = (Button) findViewById((R.id.eventComCancelBtn));
        eventComCancelBtn.setOnClickListener(this);

        TextView titleString = (TextView) findViewById(R.id.eventTitleViewText);
        User user = this.dh.getUserById(thisEvent.getOwnerId());
        titleString.setText(thisEvent.getTitle() + " - " + user.get_userName());
        TextView desString = (TextView) findViewById(R.id.eventDesViewText);
        desString.setText(thisEvent.getDesciption());

        LinearLayout commentLayout = (LinearLayout) findViewById(R.id.eventCommentLinearLayout);
        List<Integer> allCommentIds = thisEvent.getCommentId();

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.eventComBtn:
                addComment();
                finish();
                break;
            case R.id.eventComCancelBtn:
                finish();
                break;
        }
    }

    private void addComment(){

    }

}
