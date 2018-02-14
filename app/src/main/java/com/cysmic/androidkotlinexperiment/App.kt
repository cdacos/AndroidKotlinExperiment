package com.cysmic.androidkotlinexperiment

import android.app.Activity
import android.app.Application
import com.cysmic.androidkotlinexperiment.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by carlos on 14/02/18.
 */

class App : Application(), HasActivityInjector {
  @Inject
  internal var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null

  override fun onCreate() {
    super.onCreate()
    AppInjector.init(this)
  }

  override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
    return dispatchingAndroidInjector
  }
}
