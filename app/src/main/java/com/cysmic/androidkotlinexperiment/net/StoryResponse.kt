package com.cysmic.androidkotlinexperiment.net

import com.cysmic.androidkotlinexperiment.model.Story

interface StoryResponse {
  fun onStoryResponse(story: Story?, message: String?)
}
