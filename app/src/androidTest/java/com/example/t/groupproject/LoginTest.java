package com.example.t.groupproject;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.example.t.groupproject.View.Login;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

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

public class LoginTest {
    @Rule
    public ActivityTestRule<Login> mLoginActivityRule = new ActivityTestRule(Login.class);

    @Ignore
    @Test
    public void randomUNOnly() throws InterruptedException {
        //random username only
        onView(withId(R.id.emailField)).perform(clearText(), typeText("abcd@gmail.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));

    }

    @Ignore
    @Test
    public void registeredUNOnly() {
        //registered username only
        onView(withId(R.id.emailField)).perform(clearText(), typeText("jianxiahonglewis@gmail.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        onView(withText("Pleas enter password"))
                .inRoot(withDecorView(not(is(mLoginActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    @Ignore
    @Test
    public void pwdOnly() throws InterruptedException {
        //pwd only
        onView(withId(R.id.emailField)).perform(clearText());
        onView(withId(R.id.passwordField)).perform(clearText(), typeText("asdfasdf"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        Thread.sleep(50);
        onView(withText("Pleas enter email"))
                .inRoot(withDecorView(not(is(mLoginActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    @Ignore
    @Test
    public void rdmUNNpwd() {
        //random username & pwd
        onView(withId(R.id.emailField)).perform(clearText(), typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.passwordField)).perform(clearText(), typeText("ssegggh"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        //Espresso.onView(withId(R.id.textViewSignUp)).check(matches(withText("Does not have an account? Sign up here")));
        onView(withText("Could not login, incorrect credential"))
                .inRoot(withDecorView(not(is(mLoginActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    @Ignore
    @Test
    public void registeredUNIvldPwd() {
        //registered username & wrong pwd
        onView(withId(R.id.emailField)).perform(clearText(), typeText("yiditheawe1@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordField)).perform(clearText(), typeText("123457"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        onView(withText("Could not login, incorrect credential"))
                .inRoot(withDecorView(not(is(mLoginActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    @Test
    public void signUp() throws InterruptedException {
        //? jump to sign up page
        onView(withId(R.id.textViewSignUp)).perform(click());
        Thread.sleep(50);
        onView(withId(R.id.registerButton)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewLogin)).perform(click());
    }

    @Ignore
    @Test
    public void registeredUNPwd() throws InterruptedException {
        //? registered username & correct pwd      (toast returns "" )
        onView(withId(R.id.emailField)).perform(clearText(), typeText("xiaoxiuzhe@163.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordField)).perform(clearText(), typeText("123456"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        Thread.sleep(50);
        onView(withId(R.id.homeTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.logOutButton)).perform(click());
    }

}