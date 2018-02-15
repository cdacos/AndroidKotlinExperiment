package com.cysmic.androidkotlinexperiment.ui

import com.cysmic.androidkotlinexperiment.model.Story

interface StoryClickCallback {
  fun onStoryClick(story: Story)
  fun onStoryCommentsClick(story: Story)
}