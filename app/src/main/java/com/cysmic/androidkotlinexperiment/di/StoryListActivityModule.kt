package com.cysmic.androidkotlinexperiment.di

import com.cysmic.androidkotlinexperiment.ui.StoryListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StoryListActivityModule {
  @ContributesAndroidInjector
  abstract fun contributeStoryListActivity(): StoryListActivity
}

