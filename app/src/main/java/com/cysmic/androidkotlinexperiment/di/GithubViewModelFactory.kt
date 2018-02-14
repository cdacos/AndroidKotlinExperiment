package com.cysmic.androidkotlinexperiment.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

// For original Java file and a copy of the Apache licence, see:
// https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample

@Singleton
class GithubViewModelFactory @Inject
internal constructor(private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    var creator: Provider<out ViewModel>? = creators[modelClass]
    if (creator == null) {
      for ((key, value) in creators) {
        if (modelClass.isAssignableFrom(key)) {
          creator = value
          break
        }
      }
    }
    if (creator == null) {
      throw IllegalArgumentException("unknown model class " + modelClass)
    }
    try {
      return creator.get() as T
    }
    catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}
