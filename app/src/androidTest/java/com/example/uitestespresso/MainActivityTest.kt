package com.example.uitestespresso

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    fun test_showDialog_captureNameInput() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val EXPECTED_NAME = "Mitch"


        onView(withId(R.id.button_launch_dialog)).perform(click())

        //We can do withId but we can also do withText and it will look for view with that text
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        onView(withText(R.string.text_ok)).perform(click())

        //Bcz text is empty it should still be in display
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        //Enter some input (md_input_message is the id of text view in the dialog library,obviously
        // we can use withText)
        onView(withId(R.id.md_input_message)).perform(typeText(EXPECTED_NAME))

        onView(withText(R.string.text_ok)).perform(click())

        //Now dialog should not be in view,we need to use not()
        //Below will fail , bcz the view is not at all present in that level ,it's gone
        //onView(withText(R.string.text_enter_name)).check(matches(not(isDisplayed())))
        onView(withText(R.string.text_enter_name)).check(doesNotExist())

        //cnf if name is set on textview
        onView(withId(R.id.text_name)).check(matches(withText(EXPECTED_NAME)))

        onView(withText(MainActivity.buildToastMessage(EXPECTED_NAME)))
            //Makes this ViewInteraction scoped to the root selected by the given root matcher
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))


    }


}