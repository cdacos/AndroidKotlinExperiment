package com.cysmic.androidkotlinexperiment.net

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.cysmic.androidkotlinexperiment.model.Story
import com.google.gson.Gson
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
    Log.d("FetchStoryListAsync", "")
    poolExecutor.execute {
      var message = ""
      var list: List<Int>? = null

      try {
        val url = URL("https://hacker-news.firebaseio.com/v0/topstories.json")
        val reader = InputStreamReader(url.openStream())
        list = Gson().fromJson(reader, Array<Int>::class.java).toList()
      }
      catch (e: Exception) {
        message = String.format(Locale.US, "Error loading feed: %s. Check your connectivity.", e.localizedMessage)
      }

      Log.d("FetchStoryListAsyncX", list.toString())

      if (callback != null) {
        Handler(Looper.getMainLooper()).post {
          callback.onStoryListResponse(list, message)
        }
      }
    }
  }

  fun fetchStory(id: String, callback: StoryResponse?) {
    Log.d("StoryRequest", id)
    poolExecutor.execute {
      var message = ""
      var story: Story? = null

      try {
        val url = URL(String.format(Locale.US, "https://hacker-news.firebaseio.com/v0/item/%s.json", id))
        val reader = InputStreamReader(url.openStream())
        story = Gson().fromJson(reader, Story::class.java)
      }
      catch (e: Exception) {
        message = String.format(Locale.US, "Error loading story: %s. Check your connectivity.", e.localizedMessage)
      }

      Log.d("FetchStoryAsyncX", story.toString())

      if (callback != null) {
        Handler(Looper.getMainLooper()).post {
          callback.onStoryResponse(story, message)
        }
      }
    }
  }
}
