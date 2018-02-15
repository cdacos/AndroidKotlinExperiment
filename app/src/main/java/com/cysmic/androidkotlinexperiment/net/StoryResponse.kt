package com.cysmic.androidkotlinexperiment.net

import com.cysmic.androidkotlinexperiment.model.Story

interface StoryResponse {
  fun onStoryListResponse(list: List<Int>?, message: String?)
  fun onStoryResponse(story: Story?, message: String?)
}
