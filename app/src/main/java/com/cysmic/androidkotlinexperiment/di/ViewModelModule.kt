package com.cysmic.androidkotlinexperiment.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cysmic.androidkotlinexperiment.model.StoryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(StoryListViewModel::class)
  abstract fun bindStoryListViewModel(storyListViewModel: StoryListViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: GithubViewModelFactory): ViewModelProvider.Factory
}
