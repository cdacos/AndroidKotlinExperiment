package com.cysmic.androidkotlinexperiment.net

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.cysmic.androidkotlinexperiment.model.Story
import com.google.gson.Gson
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StoryRequest @Inject constructor() {
  private val poolExecutor: ThreadPoolExecutor

  init {
    val requestQueue = LinkedBlockingQueue<Runnable>()
    val corePoolSize = Runtime.getRuntime().availableProcessors()
    poolExecutor = ThreadPoolExecutor(
        corePoolSize,
        4 * corePoolSize,
        1,
        TimeUnit.SECONDS,
        requestQueue)
  }

  fun fetchList(callback: StoryResponse?) {
    poolExecutor.execute {
      var message = ""
      var list: List<Int>? = null

      try {
        val url = "https://hacker-news.firebaseio.com/v0/topstories.json"
        val reader = InputStreamReader(getURLStream(url))
        list = Gson().fromJson(reader, Array<Int>::class.java).toList()
      }
      catch (e: Exception) {
        message = String.format(Locale.US, "Error loading feed: %s. Check your connectivity.", e.localizedMessage)
      }

      if (callback != null) {
        Handler(Looper.getMainLooper()).post {
          callback.onStoryListResponse(list, message)
        }
      }
    }
  }

  fun fetchStory(id: String, callback: StoryResponse?) {
    poolExecutor.execute {
      var message = ""
      var story: Story? = null

      try {
        val url = String.format(Locale.US, "https://hacker-news.firebaseio.com/v0/item/%s.json", id)
        val reader = InputStreamReader(getURLStream(url))
        story = Gson().fromJson(reader, Story::class.java)
        if (story == null) throw RuntimeException("Failed to download story")
      }
      catch (e: Exception) {
        message = String.format(Locale.US, "Error loading story [%s]: %s. Check your connectivity.", id, e.localizedMessage)
      }

      Log.d("FetchStory", story.toString())

      if (callback != null) {
        Handler(Looper.getMainLooper()).post {
          callback.onStoryResponse(story, message)
        }
      }
    }
  }

  private fun getURLStream(url: String) : InputStream {
    val url = URL(url)
    val conn = url.openConnection()
    conn.connectTimeout = 5000
    conn.readTimeout = 5000
    return conn.getInputStream()
  }
}
