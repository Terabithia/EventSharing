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
    private final static String USER_ID = "userID";
    int clickEventID;
    Event thisEvent;
    private EditText commentEdit;
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
        commentEdit = (EditText) findViewById(R.id.commentTextEdit);

        LinearLayout commentLayout = (LinearLayout) findViewById(R.id.eventCommentLinearLayout);
        List<Integer> allCommentIds = this.dh.getCommentsByEventId(clickEventID);
        for (Integer cId : allCommentIds){
            TextView tv = new TextView(this);
            Comment c = this.dh.getCommentById(cId);
            User u = this.dh.getUserById(c.getUserId());
            tv.setText(u.get_userName() + ": " + c.getContent());
            commentLayout.addView(tv);
        }
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
        String text = commentEdit.getText().toString();
        int myID = PreferenceManager.getDefaultSharedPreferences(this)
                .getInt(USER_ID, 0);
        this.dh.insertComment(clickEventID,text,myID);
    }

}
