package com.gunt.kakaosearchrevision.di

import com.gunt.kakaosearchrevision.navigator.AppNavigator
import com.gunt.kakaosearchrevision.navigator.NavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {

    @Binds
    abstract fun bindNavigator(navigationImpl: NavigationImpl): AppNavigator

}