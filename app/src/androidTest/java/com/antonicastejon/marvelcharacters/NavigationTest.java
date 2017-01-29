package com.antonicastejon.marvelcharacters;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.antonicastejon.marvelcharacters.views.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Antoni Castejón on 30/01/2017.
 */
@RunWith(AndroidJUnit4.class)
public class NavigationTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testNavigation() {
        onView(ViewMatchers.withId(R.id.recycler_view))
                .check(matches(isDisplayed()));

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.text_title))
                .check(matches(isDisplayed()));

        onView(withId(R.id.text_description))
                .check(matches(isDisplayed()));

        onView(withId(R.id.text_pages))
                .check(matches(isDisplayed()));
    }


}
