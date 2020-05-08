package com.example.uitestespresso

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.uitestespresso", appContext.packageName)
    }


    @Test
    fun test_isActivityInView() {
        //This will launch the activity to be checked,this needs to be called everytime if u are
        //testing each method seprately , there are ways to do it globally
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //Just checks if the mainActivity is in view when we open the app
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_title_nextButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.activity_main_title)).check(matches(isDisplayed()))

        onView(withId(R.id.button_next_activity)).check(matches(isDisplayed()))

        //This also can be used ,above and below does the same job
        //onView(withId(R.id.activity_main_title)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_isTitleTextDisplayed() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //Will test if the text displayed in text is right
        onView(withId(R.id.activity_main_title)).check(matches(withText(R.string.text_mainactivity)))

    }


    //Check if navigation is working between activities
    @Test
    fun test_navSecondaryActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button_next_activity)).perform(click())
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
        onView(withId(R.id.button_back)).perform(click())
        // pressBack() If u want to check it with pressing back button
        onView(withId(R.id.main)).check(matches(isDisplayed()))

    }


}
