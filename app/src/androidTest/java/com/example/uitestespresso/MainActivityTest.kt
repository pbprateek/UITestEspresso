package com.example.uitestespresso

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.InstrumentationConnection
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    //If we are testing intents , we need to add a rule, it basically tells to prepare everything
    //Required to test intents

    @get:Rule
    val intentTestRules = IntentsTestRule(MainActivity::class.java)

    @Test
    fun test_validateGallaryIntent() {

        //  GIVEN
        val expectedIntent: Matcher<Intent> = allOf(
            hasAction(Intent.ACTION_PICK),
            hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //hasExtraWithKey()  //If u have extra data u pass to intent
        )

        val activityResult = createGallaryPickActivityResultStub()

        //So basically we are saying that , if u should see expectedIntent when i perform the click,
        //And u should be responding with activityResult when u see that intent
        //This is handled by intentTestRules
        intending(expectedIntent).respondWith(activityResult)

        //Execute and verify
        onView(withId(R.id.button_open_gallery)).perform(click())

        //Now we will see if it is returning the current intent,so did what was intended to happen,happened?
        intended(expectedIntent)


    }


    private fun createGallaryPickActivityResultStub(): Instrumentation.ActivityResult {
        //This is how u get resource refrence in test env
        //There are other classes like InstrumentationRegistry to get diff things
        //required at test time

        val resource: Resources = InstrumentationRegistry.getInstrumentation().context.resources

        //Every device is guranteed to have this ic_launcher_back image,
        //that's why we are using that to create our return uri,we can't gurantee if every device will have
        //Some image in downloads with same name
        val imageUri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resource.getResourcePackageName(R.drawable.ic_launcher_background) + "/" +
                    resource.getResourceTypeName(R.drawable.ic_launcher_background) + "/" +
                    resource.getResourceEntryName(R.drawable.ic_launcher_background)
        )

        val resultIntent = Intent()
        //resultIntent.data = imageUri

        resultIntent.data = Uri.EMPTY
        return Instrumentation.ActivityResult(RESULT_OK, resultIntent)

    }
}