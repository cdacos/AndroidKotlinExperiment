package com.cysmic.androidkotlinexperiment.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.cysmic.androidkotlinexperiment.net.FetchStoryAsync
import com.cysmic.androidkotlinexperiment.net.FetchStoryListAsync
import com.cysmic.androidkotlinexperiment.net.StoryListResponse
import com.cysmic.androidkotlinexperiment.net.StoryResponse
import javax.inject.Inject

class StoryRepository @Inject constructor() : StoryListResponse, StoryResponse {
  @Inject lateinit var fetchStoryListAsync: FetchStoryListAsync
  @Inject lateinit var fetchStoryAsync: FetchStoryAsync

  private val data: MutableLiveData<List<Story>> = MutableLiveData()
  private val stories = ArrayList<Story>()

  fun loadData() {
    fetchStoryListAsync.executeWithCallback(this)
  }

  fun getData() : LiveData<List<Story>> {
    return data
  }

  override fun onStoryListResponse(list: List<Int>, message: String?) {
    list.map { fetchStoryAsync.executeWithCallback(it.toString(), this) }
    Log.d("StoryRepository", message ?: "null")
  }

  override fun onStoryResponse(story: Story?, message: String?) {
    if (story != null) {
      stories.add(story)
      data.value = ArrayList(stories)
    }
    Log.d("StoryRepository", message ?: "null")
  }
}