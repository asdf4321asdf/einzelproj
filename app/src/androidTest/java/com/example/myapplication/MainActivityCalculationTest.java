package com.example.myapplication;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityCalculationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityCalculationTest() {
        String parameters[] = {"01637943", "10000000", "00000000", "01010101", ""};
        String expected[] = {"100001", "1", "0", "100", "0"};
        // parameterized tests in espresso?
        checkCalculationWithParameter(parameters, expected);
    }

    private void checkCalculationWithParameter(String message[], String expected[]) {
        for (int i = 0; i < message.length; ++i) {
            ViewInteraction appCompatEditText = onView(
                    allOf(withId(R.id.editText),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    0),
                            isDisplayed()));
            appCompatEditText.perform(click());

            ViewInteraction appCompatEditText2 = onView(
                    allOf(withId(R.id.editText),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    0),
                            isDisplayed()));
            appCompatEditText2.perform(replaceText(message[i]), closeSoftKeyboard());

            ViewInteraction appCompatButton = onView(
                    allOf(withId(R.id.button2), withText("calculate"),
                            childAtPosition(
                                    childAtPosition(
                                            withId(android.R.id.content),
                                            0),
                                    2),
                            isDisplayed()));
            appCompatButton.perform(click());

            onView(withText(expected[i])).check(matches(isDisplayed()));

            ViewInteraction appCompatButton2 = onView(
                    allOf(withId(android.R.id.button1), withText("OK"),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.widget.ScrollView")),
                                            0),
                                    3)));
            appCompatButton2.perform(scrollTo(), click());
        }
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
