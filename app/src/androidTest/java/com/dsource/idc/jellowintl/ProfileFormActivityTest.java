package com.dsource.idc.jellowintl;

import android.content.Context;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.dsource.idc.jellowintl.utility.SessionManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfileFormActivityTest {

    @Rule
    public ActivityTestRule<ProfileFormActivity> activityRule =
            new ActivityTestRule<>(ProfileFormActivity.class, false, false);

    @Before
    public void setup(){
        Context context = getInstrumentation().getTargetContext();
        SessionManager manager = new SessionManager(context);
        manager.setCaregiverNumber("9653238072");
        manager.setUserCountryCode("91");
        activityRule.launchActivity(null);
    }

    @Test
    public void validateUserName(){
    onView(withId(R.id.etName)).perform(clearText());
    onView(withId(R.id.parentScroll)).perform(swipeUp(), swipeUp());
    onView(withId(R.id.bSave)).perform(click());
    onView(withText(R.string.enterTheName))
            .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
            .check(matches(isDisplayed()));
    }

    @Test
    public void validateCaregiverNumber(){
        try {
            onView(withId(R.id.etName)).perform(clearText(), typeText("Akash"), closeSoftKeyboard());
            onView(withId(R.id.etFathercontact)).perform(clearText());
            onView(withId(R.id.bSave)).perform(click());
            onView(withText(R.string.enternonemptycontact))
                    .inRoot(withDecorView(not(is(
                            activityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            Thread.sleep(1500);
            onView(withId(R.id.etFathercontact)).perform(typeText("("), closeSoftKeyboard());
            onView(withId(R.id.bSave)).perform(click());
            onView(withText(R.string.enternonemptycontact))
                    .inRoot(withDecorView(not(is(
                            activityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            Thread.sleep(1500);
            onView(withId(R.id.etFathercontact)).perform(
                    clearText(),
                    typeText("number in text"),
                    closeSoftKeyboard());
            onView(withId(R.id.bSave)).perform(click());
            onView(withText(R.string.enternonemptycontact))
                    .inRoot(withDecorView(not(is(
                            activityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            Thread.sleep(1500);
            onView(withId(R.id.etFathercontact)).perform(
                    clearText(),
                    typeText("5464+4563"),
                    closeSoftKeyboard());
            onView(withId(R.id.bSave)).perform(click());
            onView(withText(R.string.enternonemptycontact))
                    .inRoot(withDecorView(not(is(
                            activityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateEmailId(){
        try {
            onView(withId(R.id.etName)).perform(typeText("Akash"), closeSoftKeyboard());
            onView(withId(R.id.etFathercontact)).perform(typeText("9653238072"), closeSoftKeyboard());
            onView(withId(R.id.bSave)).perform(click());
            onView(withText(R.string.invalid_emailId)).inRoot(withDecorView(not(is(
                    activityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            Thread.sleep(1500);
            onView(withId(R.id.parentScroll)).perform(swipeUp());
            onView(withId(R.id.etEmailId)).perform(typeText("jellowcommunicatorgmail.com"), closeSoftKeyboard());
            onView(withId(R.id.bSave)).perform(click());
            onView(withText(R.string.invalid_emailId)).inRoot(withDecorView(not(is(
                    activityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            Thread.sleep(1500);
            onView(withId(R.id.etEmailId)).perform(clearText(), typeText("jellowcommunicator@gmailcom"), closeSoftKeyboard());
            onView(withId(R.id.bSave)).perform(click());
            onView(withText(R.string.invalid_emailId)).inRoot(withDecorView(not(is(
                    activityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            Thread.sleep(1500);
            onView(withId(R.id.etEmailId)).perform(clearText(), typeText("jellowcommunicator@gmail"), closeSoftKeyboard());
            onView(withId(R.id.bSave)).perform(click());
            onView(withText(R.string.invalid_emailId)).inRoot(withDecorView(not(is(
                    activityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            Thread.sleep(1500);
            onView(withId(R.id.etEmailId)).perform(clearText(), typeText("@gmail.com"), closeSoftKeyboard());
            onView(withId(R.id.bSave)).perform(click());
            onView(withText(R.string.invalid_emailId)).inRoot(withDecorView(not(is(
                    activityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateAppRegistrationProcess(){
        //Fill form data
        try {
            onView(withId(R.id.etName)).perform(typeText("Akash"), closeSoftKeyboard());
            onView(withId(R.id.etFathercontact)).perform(typeText("9653238072"),
                    closeSoftKeyboard());
            onView(withId(R.id.parentScroll)).perform(swipeUp());
            onView(withId(R.id.etEmailId)).perform(click(), typeText(
                    "jellowcommunicator@gmail.com"), closeSoftKeyboard());
            onView(withId(R.id.radioTherapist)).perform(click(), closeSoftKeyboard());
            onView(withId(R.id.bSave)).perform(click());
            Thread.sleep(500);
            onView(withText(R.string.checkConnectivity)).inRoot(withDecorView(not(is(
                    activityRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (Exception e){
            return;
        }
    }
}