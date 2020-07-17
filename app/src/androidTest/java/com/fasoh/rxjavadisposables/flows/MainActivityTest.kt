package com.fasoh.rxjavadisposables.flows

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.fasoh.rxjavadisposables.R
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @Rule @JvmField  val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerView() {
        onView(withId(R.id.recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MainActivity.RateAdapter.RateViewHolder>(
                0,
                click()
            )
        )
        onView(withText("Clicked"))
            .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }
}