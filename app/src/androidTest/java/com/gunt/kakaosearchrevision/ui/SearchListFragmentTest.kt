package com.gunt.kakaosearchrevision.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.data.domain.DummyBookData
import com.gunt.kakaosearchrevision.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class SearchListFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    val LIST_ITEM_IN_TEST = 2
    val BOOK_IN_TEST = DummyBookData.books[LIST_ITEM_IN_TEST]

    @Before
    fun setUp() {
        hiltRule.inject()
        launchFragmentInHiltContainer<SearchListFragment>()
    }

    @Test
    fun fragmentInViewTest() {
        onView(withId(R.id.edit_search)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_search)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}
