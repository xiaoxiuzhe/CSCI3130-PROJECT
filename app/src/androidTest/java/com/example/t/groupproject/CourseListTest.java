package com.example.t.groupproject;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class CourseListTest {

    @Rule
    public ActivityTestRule<CourseLists> mActivityRule = new ActivityTestRule<>(CourseLists.class, true, false);


    @Test
    public void CheckHeaderExist()  {
        onView(withId(R.id.textView9));
    }

    @Test
    public void CheckCourseListExist(){
        onView(withId(R.id.courseListLayout));
    }

}
