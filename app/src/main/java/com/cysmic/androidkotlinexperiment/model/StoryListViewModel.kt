package com.cysmic.androidkotlinexperiment.model

import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class StoryListViewModel @Inject constructor(storyRepository: StoryRepository) : ViewModel() {
  fun getStories(): List<Story> {
    return ArrayList()
  }
}