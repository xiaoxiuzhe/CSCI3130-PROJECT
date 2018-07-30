package com.example.t.groupproject;
import android.support.test.rule.ActivityTestRule;

import com.example.t.groupproject.View.CourseLists;

import org.junit.Rule;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class CourseListTest {

    @Rule
    public ActivityTestRule<CourseLists> mActivityRule = new ActivityTestRule<>(CourseLists.class, true, false);



}
