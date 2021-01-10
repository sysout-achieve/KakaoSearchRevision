package com.gunt.kakaosearchrevision.navigator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.gunt.kakaosearchrevision.R
import com.gunt.kakaosearchrevision.ui.BookDetailFragment
import com.gunt.kakaosearchrevision.ui.SearchListFragment
import javax.inject.Inject

class NavigationImpl @Inject constructor(private var activity: FragmentActivity) : AppNavigator {

    override fun navigateTo(screens: Screens) {
        val fragment = when (screens) {
            Screens.Detail -> BookDetailFragment()
            Screens.List -> SearchListFragment()
        }
        navigate(fragment)
    }

    override fun navigateTo(screens: Screens, argument: Bundle) {
        val fragment = when (screens) {
            Screens.Detail -> BookDetailFragment().apply { arguments = argument }
            Screens.List -> SearchListFragment()
        }
        navigate(fragment)
    }

    private fun navigate(fragment:Fragment){
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commit()
    }
}