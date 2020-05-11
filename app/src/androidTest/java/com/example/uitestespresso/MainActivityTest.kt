package com.example.uitestespresso

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.InstrumentationConnection
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.uitestespresso.ImageViewHasDrawableMatcher.hasDrawable
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val intentTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun test_cameraIntent_isBitmapSetToImageView() {
        //GIVEN
        val activityResult = createImageCaptureActivityResultStub()
        //We only need to check for action so we will use hasAction and not allOf
        val expectedIntent: Matcher<Intent> = hasAction(MediaStore.ACTION_IMAGE_CAPTURE)

        //So the basic idea is , if espresso sees expectedIntent then it will return with activityResult
        //If u comment below line,and u perform click,
        //It will throw the expectedIntent but espresso dosen't know what to do with it,so ur test will be stuck
        intending(expectedIntent).respondWith(activityResult)

        //EXECUTE AND VERIFY

        //There should not be any image in imageview,so check for that
        //Matches takes parameter a matcher which has generic as ImageView or T
        onView(withId(R.id.image)).check(matches(not(hasDrawable())))
        onView(withId(R.id.button_launch_camera)).perform(click())
        //This will just check if expectedIntent was thrown or not when we click
        intended(expectedIntent)

        //So after that espresso will throw activityResult intent and using that it will put the image_background in the imageview,
        //After which we can call hasDrawable() which will tell us if image was set.

        onView(withId(R.id.image)).check(matches(hasDrawable()))


    }

    private fun createImageCaptureActivityResultStub(): Instrumentation.ActivityResult? {

        //U can get context using
        //InstrumentationRegistry.getInstrumentation().context
        //Also we can get activity using intentRules

        val bundle = Bundle().apply {
            putParcelable(
                KEY_IMAGE_DATA,
                BitmapFactory.decodeResource(
                    intentTestRule.activity.resources,
                    R.drawable.ic_launcher_background
                )
            )
        }

        val resultData = Intent()
        resultData.putExtras(bundle)
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)


    }

}