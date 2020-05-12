package com.example.uitestespresso.util

import androidx.test.espresso.idling.CountingIdlingResource


//Now u will not want to have such codes in production ,
//So what u can do is create a release package and debug package and put this file in both places,
//And in release file , comment the lines of code inside increment() and decrement()
//And based on what build varient u have selected , u can get rid of such calls in production
object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        //If counter not equal to 0 then decrement
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}