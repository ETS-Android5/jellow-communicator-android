package com.dsource.idc.jellowintl;

import android.content.Context;
import android.content.Intent;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.dsource.idc.jellowintl.utility.SessionManager;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.dsource.idc.jellowintl.utility.SessionManager.ENG_IN;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class _14_LanguagePackageUpdateActivityUITest {
    private SessionManager manager;

    @Rule
    public ActivityTestRule<LanguagePackageUpdateActivity> activityRule =
            new ActivityTestRule<>(LanguagePackageUpdateActivity.class, false, false);

    @Before
    public void setup(){
        Context context = getInstrumentation().getTargetContext();
        manager = new SessionManager(context);
        manager.setCaregiverNumber("9653238072");
        Intent intent = new Intent();
        intent.putExtra("packageList",ENG_IN);
        activityRule.launchActivity(intent);
        closeSoftKeyboard();
    }

    @Test
    public void _01_progressTextTest(){
        onView(withId(R.id.progress_text)).check(matches(withText(R.string.downloadErrorMsg)));
    }

    @Test
    public void _02_progressBarTest(){
        onView(withId(R.id.pg)).check(matches(isDisplayed()));
    }

    @Test
    public void _03_downloadCountTest(){
        onView(withId(R.id.txtDownloadCount)).check(matches(isDisplayed()));
    }
}