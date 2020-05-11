package com.example.uitestespresso.factory


import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.request.RequestOptions
import com.example.uitestespresso.data.source.MoviesDataSource
import com.example.uitestespresso.ui.movie.DirectorsFragment
import com.example.uitestespresso.ui.movie.MovieDetailFragment
import com.example.uitestespresso.ui.movie.MovieListFragment
import com.example.uitestespresso.ui.movie.StarActorsFragment

class MovieFragmentFactory(
    private val requestOptions: RequestOptions? = null,
    private val moviesDataSource: MoviesDataSource? = null
) : FragmentFactory() {

    private val TAG: String = "AppDebug"

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when (className) {

            MovieListFragment::class.java.name -> {
                if (moviesDataSource != null) {
                    MovieListFragment(moviesDataSource)
                } else {
                    super.instantiate(classLoader, className)
                }
            }


            MovieDetailFragment::class.java.name -> {
                if (requestOptions != null
                    && moviesDataSource != null
                ) {
                    MovieDetailFragment(
                        requestOptions,
                        moviesDataSource
                    )
                } else {
                    //This usages the empty constructor by default for the given className,and throws runtime exception if nor able to instantiate
                    super.instantiate(classLoader, className)
                }
            }

            DirectorsFragment::class.java.name -> {
                DirectorsFragment()
            }

            StarActorsFragment::class.java.name -> {
                StarActorsFragment()
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}













