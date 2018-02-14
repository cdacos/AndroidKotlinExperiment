package com.cysmic.androidkotlinexperiment.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cysmic.androidkotlinexperiment.model.TargetListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by carlos on 14/02/18.
 */
@Module
internal abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(TargetListViewModel::class)
  internal abstract fun bindTargetListViewModel(targetListViewModel: TargetListViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(factory: GithubViewModelFactory): ViewModelProvider.Factory
}
