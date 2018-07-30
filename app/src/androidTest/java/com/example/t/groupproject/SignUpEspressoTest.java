package com.example.t.groupproject;

import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.*;

import com.example.t.groupproject.View.SignUp;

import org.junit.Rule;
import org.junit.Test;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


public class SignUpEspressoTest {

    @Rule
    public ActivityTestRule<SignUp> mActivityRule = new ActivityTestRule(SignUp.class);

    @Test
    public void CheckEmailNotInput() throws InterruptedException {
        onView(withId(R.id.passwordField)).perform(click());
        onView(withId(R.id.passwordField)).perform(typeText("123456"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerButton)).perform(click());

        //Thread.sleep(1000);
        onView(withText("Please enter email"))
                .inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }



}
