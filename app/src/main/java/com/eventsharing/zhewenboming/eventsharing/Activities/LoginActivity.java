package com.eventsharing.zhewenboming.eventsharing.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.eventsharing.zhewenboming.eventsharing.DatabaseHelper;
import com.eventsharing.zhewenboming.eventsharing.Models.User;
import com.eventsharing.zhewenboming.eventsharing.R;

import java.util.List;

public class LoginActivity extends Activity implements OnClickListener {
    private DatabaseHelper dh;
    private EditText userNameEditableField;
    private EditText passwordEditableField;
    private final static String USER_NAME = "username";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameEditableField = (EditText) findViewById(R.id.username_text);
        passwordEditableField = (EditText) findViewById(R.id.password_text);
        View btnLogin = (Button) findViewById(R.id.login_button);
        btnLogin.setOnClickListener(this);
        View btnCancel = (Button) findViewById(R.id.cancel_button);
        btnCancel.setOnClickListener(this);
        View btnNewUser = (Button) findViewById(R.id.new_user_button);
        btnNewUser.setOnClickListener(this);
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
            case R.id.login_button:
                loginClick();
                break;
            case R.id.cancel_button:
                finish();
                break;
            case R.id.new_user_button:
                startActivity(new Intent(this, AccountActivity.class));
                break;
            //
        }
    }

    private void loginClick() {
        String username = this.userNameEditableField.getText().toString();
        String password = this.passwordEditableField.getText().toString();
        this.dh = new DatabaseHelper(this);
        List<String> users = this.dh.selectAllUsers(username, password);
        if (users.size() > 0) {
            SharedPreferences settings = PreferenceManager
                    .getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(USER_NAME, username);
            editor.commit();
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Login failed")
                    .setNeutralButton("Try Again",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            }).show();
        }
    }
}
