package com.cysmic.androidkotlinexperiment.di

import android.app.Application
import com.cysmic.androidkotlinexperiment.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by carlos on 14/02/18.
 */

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, TargetListActivityModule::class))
interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(app: App)
}