package com.cysmic.androidkotlinexperiment.model

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager

@SuppressLint("StaticFieldLeak")  // No, we hold the application context by design
class ConnectivityViewModel(application: Application) : AndroidViewModel(application) {
  private val app = application
  private val data = MutableLiveData<Boolean>()

  private val isConnected: Boolean
    get() {
      val cm = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val netInfo = cm.activeNetworkInfo
      return netInfo != null && netInfo.isAvailable && netInfo.isConnected
    }

  init {
    val networkStateReceiver = object : BroadcastReceiver() {
      override fun onReceive(context: Context, intent: Intent) {
        data.value = isConnected
      }
    }
    val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    application.registerReceiver(networkStateReceiver, filter)
  }

  fun getData(): LiveData<Boolean> {
    return data
  }
}
