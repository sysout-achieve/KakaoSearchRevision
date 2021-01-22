package com.gunt.kakaosearchrevision.ui

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.data.domain.Book
import com.gunt.kakaosearchrevision.data.domain.DummyBookData
import com.gunt.kakaosearchrevision.launchFragmentInHiltContainer
import com.gunt.kakaosearchrevision.ui.viewutil.TextViewExtensions
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class BookDetailFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var expectedBook:Book

    @Before
    fun setUp() {
        hiltRule.inject()
        val bundle = Bundle()
        expectedBook = DummyBookData.books[0]
        bundle.putSerializable("book", expectedBook)
        launchFragmentInHiltContainer<BookDetailFragment>(bundle)
    }

    @Test
    fun fragmentInViewTest() {
        //after created fragment
        onView(withId(R.id.tv_title)).check(matches(withText(expectedBook.title)))
        onView(withId(R.id.tv_contents)).check(matches(withText(expectedBook.contents)))
        onView(withId(R.id.tv_author)).check(matches(withText(expectedBook.authors.toString())))
        onView(withId(R.id.tv_publisher)).check(matches(withText(expectedBook.publisher)))
        onView(withId(R.id.tv_price)).check(matches(withText(TextViewExtensions.getPriceStrWithCurrentUnit(expectedBook.price))))
        onView(withId(R.id.tv_sale_price)).check(matches(withText(TextViewExtensions.getPriceStrWithCurrentUnit(expectedBook.sale_price))))
    }

}