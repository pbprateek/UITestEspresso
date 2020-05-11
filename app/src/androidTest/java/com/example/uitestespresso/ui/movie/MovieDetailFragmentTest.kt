package com.example.uitestespresso.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bumptech.glide.request.RequestOptions
import com.example.uitestespresso.R
import com.example.uitestespresso.data.Movie
import com.example.uitestespresso.data.source.MoviesRemoteDataSource
import com.example.uitestespresso.factory.MovieFragmentFactory
import io.mockk.every
import io.mockk.mockk
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {


    @Test
    fun test_isMovieDataDisplayed() {
        //val movie = MoviesRemoteDataSource().getMovie(1)
        //Or we can mock it
        val movie = Movie(
            1,
            "The Rundown",
            "https://nyc3.digitaloceanspaces.com/open-api-spaces/open-api-static/blog/1/The_Rundown-the_rundown.png",
            "A tough aspiring chef is hired to bring home a mobster's son from the Amazon but " +
                    "becomes involved in the fight against an oppressive town operator and the search " +
                    "for a legendary treasure.",
            arrayListOf("R.J. Stewart", "James Vanderbilt"),
            arrayListOf(
                "Dwayne Johnson",
                "Seann William Scott",
                "Rosario Dawson",
                "Christopher Walken"
            )
        )

        val movieDataSource = mockk<MoviesRemoteDataSource>()
        //So basically if getMovie get's called return movie
        every {
            movieDataSource.getMovie(1)
        } returns movie //This is infix in kotlin , research more

        val requestOptions = RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)


        val fragmentFactory = MovieFragmentFactory(
            requestOptions, movieDataSource
        )


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