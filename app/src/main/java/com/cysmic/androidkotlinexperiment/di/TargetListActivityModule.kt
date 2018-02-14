package com.cysmic.androidkotlinexperiment.di

import com.cysmic.androidkotlinexperiment.StoriesListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TargetListActivityModule {
  @ContributesAndroidInjector
  internal abstract fun contributeTargetListActivity(): StoriesListActivity
}

