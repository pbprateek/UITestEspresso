package com.example.uitestespresso

import android.os.IBinder
import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

//Yes we can also use Boundedmatcher , u can say TypeSafeMatcher is a
//Simple Matcher and takes just one value
class ToastMatcher : TypeSafeMatcher<Root?>() {
    override fun describeTo(description: Description?) {
        description?.appendText("is taost")
    }

    override fun matchesSafely(item: Root?): Boolean {
        val type: Int? = item?.windowLayoutParams?.get()?.type
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            val windowToken: IBinder = item.decorView.windowToken
            val appToken: IBinder = item.decorView.applicationWindowToken
            if (windowToken === appToken) { //means this window isn't contained by any other window
                return true
            }
        }
        return false
    }
}