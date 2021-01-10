package com.gunt.kakaosearchrevision.ui

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.data.DummyBookData
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


//@RunWith(AndroidJUnit4::class)
//class BookDetailFragmentTest {
//    lateinit var fragmentScenario: FragmentScenario<BookDetailFragment>
//    //    @get: Rule
////    val fragmentActivity  = FragmentS
//    @Before
//    fun setUp() {
//        val fragmentArgs = Bundle()
//        fragmentArgs.putSerializable("book", DummyBookData().books[0])
//        fragmentScenario = launchFragmentInContainer<BookDetailFragment>(fragmentArgs = fragmentArgs , factory = )
//    }
//
//    @Test
//    fun fragmentInViewTest() {
//        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
//    }
//}