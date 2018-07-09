package com.example.t.groupproject;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class UserInfoInitTest {
    @Rule
    public ActivityTestRule<Login> mLoginActivityRule = new ActivityTestRule(UserInfoInit .class);

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
    public void testifyBtn() throws InterruptedException {
        //check if the activity is right
        onView(withId(R.id.SaveButton)).check(matches(isDisplayed()));

    }
}