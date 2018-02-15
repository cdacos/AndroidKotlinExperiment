package com.cysmic.androidkotlinexperiment.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.cysmic.androidkotlinexperiment.net.StoryRequest
import com.cysmic.androidkotlinexperiment.net.StoryResponse
import javax.inject.Inject

class StoryRepository @Inject constructor() : StoryResponse {
  @Inject lateinit var storyRequest: StoryRequest

  private val data: MutableLiveData<List<Story>> = MutableLiveData()
  private val message: MutableLiveData<String> = MutableLiveData()
  private val stories = ArrayList<Story>()

  fun loadData() {
    stories.clear()
    storyRequest.fetchList(this)
  }

  fun getData() : LiveData<List<Story>> {
    return data
  }

  fun getMessage(): LiveData<String> {
    return message
  }

  override fun onStoryListResponse(list: List<Int>?, message: String?) {
    list?.take(25)?.map { storyRequest.fetchStory(it.toString(), this) }
    this.message.value = message
  }

  override fun onStoryResponse(story: Story?, message: String?) {
    if (story != null) {
      stories.add(story)
      data.value = ArrayList(stories)
    }
    this.message.value = message
  }
}