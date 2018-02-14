package com.cysmic.androidkotlinexperiment.net

import android.os.AsyncTask
import com.cysmic.androidkotlinexperiment.model.Story
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import javax.inject.Inject

class FetchStoryListAsync @Inject constructor() : AsyncTask<Void, Void, Array<Int>>() {
  private var callback: FetchResponse? = null
  private var message: String? = null

  fun executeWithCallback(callback: FetchResponse) {
    this.callback = callback
    if (status != AsyncTask.Status.RUNNING) execute()
  }

  override fun doInBackground(vararg voids: Void): Array<Int> {
    try {
      val url = URL("https://hacker-news.firebaseio.com/v0/topstories.json")
      val reader = InputStreamReader(url.openStream())
      return Gson().fromJson(reader, Array<Int>::class.java)
    }
    catch (e: Exception) {
      message = String.format(Locale.getDefault(), "Error loading feed: %s. Check your connectivity.", e.localizedMessage)
    }

    return arrayOf()
  }

  override fun onPostExecute(items: Array<Int>) {
    val list = items.map { n -> Story(n.toString()) }
    callback!!.onFetchResponse(list, message)
  }
}
