package com.cysmic.androidkotlinexperiment.net

import com.cysmic.androidkotlinexperiment.model.Story

interface StoryListResponse {
  fun onStoryListResponse(list: List<Story>, message: String?)
}
