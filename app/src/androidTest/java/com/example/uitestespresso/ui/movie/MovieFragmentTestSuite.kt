package com.example.uitestespresso.ui.movie

import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    MovieDetailFragmentTest::class,
    StarActorsFragmentTest::class,
    DirectorsFragmentTest::class,
    MovieNavigationTest::class

)
class MovieFragmentTestSuite {
}