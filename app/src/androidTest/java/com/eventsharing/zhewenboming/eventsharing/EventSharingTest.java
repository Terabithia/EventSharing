package com.eventsharing.zhewenboming.eventsharing;

import android.test.ActivityInstrumentationTestCase2;
import com.eventsharing.zhewenboming.eventsharing.Activities.LoginActivity;
import com.robotium.solo.Solo;

/**
 * Created by Terabithia on 2015/4/17.
 */

public class EventSharingTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    public EventSharingTest() {
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

    public void testLoginActivity() throws Exception {
        // check that we have the right activity
        solo.assertCurrentActivity("wrong activity", LoginActivity.class);

    }
}

