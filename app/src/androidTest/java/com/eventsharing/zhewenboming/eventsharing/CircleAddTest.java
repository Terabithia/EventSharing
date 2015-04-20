package com.eventsharing.zhewenboming.eventsharing;

import android.test.ActivityInstrumentationTestCase2;

import com.eventsharing.zhewenboming.eventsharing.Activities.AccountActivity;
import com.eventsharing.zhewenboming.eventsharing.Activities.CircleActivity;
import com.eventsharing.zhewenboming.eventsharing.Activities.CircleAddActivity;
import com.eventsharing.zhewenboming.eventsharing.Activities.EventAddActivity;
import com.eventsharing.zhewenboming.eventsharing.Activities.HomeActivity;
import com.eventsharing.zhewenboming.eventsharing.Activities.LoginActivity;
import com.robotium.solo.Solo;

import junit.framework.Assert;

/**
 * Created by Terabithia on 2015/4/17.
 */

public class CircleAddTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    public CircleAddTest() {
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

    public void testCircleAddActivity() throws Exception {
        // check that we have the right activity
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
        solo.clickOnButton("+Circle");
        solo.assertCurrentActivity("wrong activity", CircleAddActivity.class);
        solo.enterText(0, "CircleName");
        solo.clickOnButton("Save");
        solo.assertCurrentActivity("wrong activity", HomeActivity.class);
        Assert.assertTrue(solo.searchText("CircleName"));
    }
}
