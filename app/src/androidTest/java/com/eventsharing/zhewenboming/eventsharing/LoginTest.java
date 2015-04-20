package com.eventsharing.zhewenboming.eventsharing;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.eventsharing.zhewenboming.eventsharing.Activities.AccountActivity;
import com.eventsharing.zhewenboming.eventsharing.Activities.HomeActivity;
import com.eventsharing.zhewenboming.eventsharing.Activities.LoginActivity;
import com.robotium.solo.Solo;

/**
 * Created by Terabithia on 2015/4/17.
 */

public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    public LoginTest() {
        super(LoginActivity.class);
    }
    private Solo solo;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

//    public void testLoginActivity() throws Exception {
//        // check that we have the right activity
//        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
//        solo.clickOnButton("new user");
//        solo.assertCurrentActivity("wrong activity", AccountActivity.class);
//    }
//
//    public void testNewUser() throws Exception {
//        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
//        solo.clickOnButton("new user");
//        solo.assertCurrentActivity("wrong activity", AccountActivity.class);
//        solo.enterText(0, "TestUser1");
//        solo.enterText(1, "1");
//        solo.enterText(2, "1");
////        EditText con = (EditText) solo.getView(R.id.password_confirm);
////        con.setText("1");
//        solo.clickOnButton("done");
//        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
//    }
//
//    public void testAccountCancel() throws Exception {
//        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
//        solo.clickOnButton("new user");
//        solo.assertCurrentActivity("wrong activity", AccountActivity.class);
//        solo.clickOnButton("cancel");
//        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
//    }

    public void testLogin() throws Exception {
        solo.assertCurrentActivity("wrong activity", LoginActivity.class);
        solo.clickOnButton("new user");
        solo.assertCurrentActivity("wrong activity", AccountActivity.class);
        solo.enterText(0, "TestUser1");
        solo.enterText(1, "1");
        solo.enterText(2, "1");
        solo.clickOnButton("done");
        solo.enterText(0, "TestUser1");
        solo.enterText(1, "1");
        solo.clickOnButton("login");
        solo.assertCurrentActivity("wrong activity", HomeActivity.class);
    }

}

