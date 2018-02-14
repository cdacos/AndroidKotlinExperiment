package com.cysmic.androidkotlinexperiment.net;

import com.cysmic.androidkotlinexperiment.model.Story;

import java.util.List;

public interface FetchResponse {
  void onFetchResponse(List<Story> list, String message);
}
