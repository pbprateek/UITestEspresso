package com.example.uitestespresso.data.source

import com.example.uitestespresso.data.Movie


interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?

    fun getMovies(): List<Movie>
}