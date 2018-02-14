package com.cysmic.androidkotlinexperiment.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.cysmic.androidkotlinexperiment.R
import com.cysmic.androidkotlinexperiment.model.Story

class StoryRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  private val name: TextView = itemView.findViewById(R.id.name)

  fun bindData(item: Story) {
    name.text = item.id
  }
}
