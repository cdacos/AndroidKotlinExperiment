package com.cysmic.androidkotlinexperiment.net

interface StoryListResponse {
  fun onStoryListResponse(list: List<Int>, message: String?)
}
