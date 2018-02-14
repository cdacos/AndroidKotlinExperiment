package com.cysmic.androidkotlinexperiment.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.cysmic.androidkotlinexperiment.net.StoryListResponse
import com.cysmic.androidkotlinexperiment.net.FetchStoryListAsync
import java.util.*
import javax.inject.Inject

class StoryRepository @Inject constructor() : StoryListResponse {
  @Inject lateinit var fetchStoryListAsync: FetchStoryListAsync

  private val data: MutableLiveData<List<Story>> = MutableLiveData()

  fun loadData() {
    fetchStoryListAsync.executeWithCallback(this)
  }

  fun getData(): LiveData<List<Story>> {
    return data
  }

  override fun onStoryListResponse(list: List<Story>, message: String?) {
    data.value = list
    Log.d("StoryRepository", String.format(Locale.getDefault(), "Items: %s, Message: %s", list.size, message))
  }
}