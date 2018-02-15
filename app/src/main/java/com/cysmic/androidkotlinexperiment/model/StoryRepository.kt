package com.cysmic.androidkotlinexperiment.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.cysmic.androidkotlinexperiment.net.StoryRequest
import com.cysmic.androidkotlinexperiment.net.StoryResponse
import javax.inject.Inject

class StoryRepository @Inject constructor() : StoryResponse {
  @Inject lateinit var storyRequest: StoryRequest

  private val data: MutableLiveData<List<Story>> = MutableLiveData()
  private val stories = ArrayList<Story>()

  fun loadData() {
    storyRequest.fetchList(this)
  }

  fun getData() : LiveData<List<Story>> {
    return data
  }

  override fun onStoryListResponse(list: List<Int>?, message: String?) {
    list?.map { storyRequest.fetchStory(it.toString(), this) }
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