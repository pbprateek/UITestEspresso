package com.example.uitestespresso.ui.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.uitestespresso.R
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigationTest {

    @Test
    fun testMovieFragmentsnavigation() {

        //Main Activity is the host for all fragments so we will check nav in that
        val activityScenerio = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.movie_directiors)).perform(click())

        onView(withId(R.id.fragment_directors_parent)).check(matches(isDisplayed()))

        //Now we are again in movie detail frag
        pressBack()

        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))

        //Nav to Star frag
        onView(withId(R.id.movie_star_actors)).perform(click())

        onView(withId(R.id.fragment_movie_star_fragment)).check(matches(isDisplayed()))

        //Should go back again to movie detail
        pressBack()

        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))


    }
}