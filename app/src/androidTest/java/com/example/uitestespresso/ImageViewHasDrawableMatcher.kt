package com.example.uitestespresso

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

object ImageViewHasDrawableMatcher {
    fun hasDrawable(): BoundedMatcher<View, ImageView> {
        
        //Some matcher sugar that lets you create a matcher for a given type but only process items of a specific subtype of that matcher.
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description?) {
                //It is just use for debugging,no logic here
                description?.appendText("has Drawable")
            }

            override fun matchesSafely(item: ImageView?): Boolean {
                //If there is drawable set to imageview the test will pass
                return item?.drawable != null
            }

        }


    }
}