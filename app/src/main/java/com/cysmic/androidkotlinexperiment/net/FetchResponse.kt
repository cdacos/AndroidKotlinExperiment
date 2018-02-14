package com.cysmic.androidkotlinexperiment.net

import com.cysmic.androidkotlinexperiment.model.Story

interface FetchResponse {
  fun onFetchResponse(list: List<Story>, message: String?)
}
