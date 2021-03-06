package com.eventsharing.zhewenboming.eventsharing.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eventsharing.zhewenboming.eventsharing.DatabaseHelper;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
import com.eventsharing.zhewenboming.eventsharing.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountActivity extends Activity implements OnClickListener {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirm;
    private DatabaseHelper dh;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        etConfirm = (EditText) findViewById(R.id.password_confirm);
        View btnAdd = (Button) findViewById(R.id.done_button);
        btnAdd.setOnClickListener(this);
        View btnCancel = (Button) findViewById(R.id.cancel_button);
        btnCancel.setOnClickListener(this);
    }

    private void CreateAccount() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();
        if ((password.equals(confirm)) && (!username.equals(""))
                && (!password.equals("")) && (!confirm.equals(""))) {
            this.dh = new DatabaseHelper(this);
            this.dh.insertUser(username, password);
            User myUser = this.dh.getUserByName(username);
            List<Integer> friendList = new ArrayList<Integer>();
            friendList.add(myUser.getId());
            myUser.setFriends(friendList);
            this.dh.addFriendById(myUser.getId(),myUser.getId());
            Toast.makeText(AccountActivity.this, "new record inserted",
                    Toast.LENGTH_SHORT).show();
            finish();
        } else if ((username.equals("")) || (password.equals(""))
                || (confirm.equals(""))) {
            Toast.makeText(AccountActivity.this, "Missing entry", Toast.LENGTH_SHORT)
                    .show();
        } else if (!password.equals(confirm)) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("passwords do not match")
                    .setNeutralButton("Try Again",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            })
                    .show();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done_button:
                CreateAccount();
                finish();
                break;
            case R.id.cancel_button:
                finish();
                break;
        }
    }
}
