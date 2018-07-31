package com.example.t.groupproject;
import android.support.test.rule.ActivityTestRule;

import com.example.t.groupproject.View.Login;
import com.example.t.groupproject.View.UserInfo;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


public class UserInfoTest {
    @Rule
    public ActivityTestRule<Login> mLoginActivityRule = new ActivityTestRule(UserInfo.class);

    @Test
    public void testifyName() throws InterruptedException {
        //check if the activity is right
        onView(withId(R.id.name)).check(matches(isDisplayed()));
    }

    @Test
    public void testifyPhone() throws InterruptedException {
        //check if the activity is right
        onView(withId(R.id.phone)).check(matches(isDisplayed()));
    }

    @Test
    public void testifyAddress() throws InterruptedException {
        //check if the activity is right
        onView(withId(R.id.address)).check(matches(isDisplayed()));
    }

    @Test
    public void testifyMajor() throws InterruptedException {
        //check if the activity is right
        onView(withId(R.id.Major)).check(matches(isDisplayed()));

    }

    @Test
    public void testifyPwd() throws InterruptedException {
        //check if the activity is right
        onView(withId(R.id.password)).check(matches(isDisplayed()));

    }

    @Test
    public void testifyPwdBtn() throws InterruptedException {
        //check if the activity is right
        onView(withId(R.id.changePasswordButton)).check(matches(isDisplayed()));

    }

//出现跳页
    @Test
    public void resetInfo() throws InterruptedException {
        //check if the activity is right
        Thread.sleep(50);
        onView(withId(R.id.name)).perform(clearText(), typeText("Test"), closeSoftKeyboard());
        Thread.sleep(50);
        onView(withId(R.id.address)).perform(clearText(), typeText("main st"), closeSoftKeyboard());
        Thread.sleep(50);
        onView(withId(R.id.phone)).perform(clearText(), typeText("9999999999"), closeSoftKeyboard());
        Thread.sleep(50);
        onView(withId(R.id.Major)).perform(click());
       // R.id.Major.
        onView(withId(R.id.SaveButton)).perform(click());
       // mLoginActivityRule = new ActivityTestRule(Home.class);
       // onView(withText("Password changed")).inRoot(withDecorView(not(is(mLoginActivityRule.getActivity().getWindow().getDecorView())))).perform(click());
    }

}