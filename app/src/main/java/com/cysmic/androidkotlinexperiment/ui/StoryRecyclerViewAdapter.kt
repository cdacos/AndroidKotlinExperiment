package com.cysmic.androidkotlinexperiment.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cysmic.androidkotlinexperiment.R
import com.cysmic.androidkotlinexperiment.model.Story
import com.cysmic.androidkotlinexperiment.model.StoryListViewModel

class StoryRecyclerViewAdapter(private val model: StoryListViewModel) : RecyclerView.Adapter<StoryRecyclerViewHolder>() {

  @SuppressLint("InflateParams")
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryRecyclerViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.story_details, parent, false)
    return StoryRecyclerViewHolder(view)
  }

  override fun onBindViewHolder(holder: StoryRecyclerViewHolder, position: Int) {
    holder.bindData(model.getData().value?.get(position) ?: Story("")) //TODO Tidy!
  }

  override fun getItemCount(): Int {
    return model.getData().value?.size ?: 0
  }
}
