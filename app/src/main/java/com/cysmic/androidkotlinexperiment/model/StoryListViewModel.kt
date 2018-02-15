package com.cysmic.androidkotlinexperiment.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class StoryListViewModel @Inject constructor(private val repository: StoryRepository) : ViewModel() {
  fun loadData(): Boolean {
    if (getData().value?.isEmpty() != false) {
      repository.loadData()
      return true
    }
    return false
  }

  fun reloadData() {
    repository.loadData()
  }

  fun getData(): LiveData<List<Story>> {
    return repository.getData()
  }

  fun getMessage(): LiveData<String> {
    return repository.getMessage()
  }
}