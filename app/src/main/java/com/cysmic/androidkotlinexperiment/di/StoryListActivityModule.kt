package com.cysmic.androidkotlinexperiment.di

import com.cysmic.androidkotlinexperiment.StoryListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StoryListActivityModule {
  @ContributesAndroidInjector
  abstract fun contributeStoryListActivity(): StoryListActivity
}

