package com.example.uitestespresso.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.uitestespresso.R
import com.example.uitestespresso.data.DummyMovies.THE_RUNDOWN
import com.example.uitestespresso.factory.MovieFragmentFactory
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {


    @Test
    fun test_isMovieDataDisplayed() {
        val movie = THE_RUNDOWN
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle().apply {
            putInt("movie_id", movie.id)
        }
        //This will launch the fragment in isolation,basically inside an empty fragmentActivity
        val scenario = launchFragmentInContainer<MovieDetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )
        onView(withId(R.id.movie_title)).check(matches(withText(movie.title)))
        onView(withId(R.id.movie_description)).check(matches(withText(movie.description)))
    }



}