package com.cysmic.androidkotlinexperiment.ui

import android.annotation.SuppressLint
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cysmic.androidkotlinexperiment.R
import com.cysmic.androidkotlinexperiment.model.Story
import kotlin.properties.Delegates

class StoryListRecyclerViewAdapter : RecyclerView.Adapter<StoryListRecyclerViewAdapter.StoryRecyclerViewHolder>() {
  // I first saw this clever idea mentioned at https://antonioleiva.com/recyclerview-diffutil-kotlin/
  var items: List<Story> by Delegates.observable(emptyList()) {
    _, oldList, newList ->
    notifyChanges(oldList, newList)
  }

  private fun notifyChanges(oldList: List<Story>, newList: List<Story>) {
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
      override fun getOldListSize(): Int {
        return oldList.size
      }

      override fun getNewListSize(): Int {
        return newList.size
      }

      override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
      }

      override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition] // Kotlin data classes comparison
      }
    })
    diff.dispatchUpdatesTo(this)
  }

  @SuppressLint("InflateParams")
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryRecyclerViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.story_details, parent, false)
    return StoryRecyclerViewHolder(view)
  }

  override fun onBindViewHolder(holder: StoryRecyclerViewHolder, position: Int) {
    val story = items[position]
    holder.name.text = story.id
  }

  override fun getItemCount(): Int {
    return items.size
  }

  class StoryRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.name)
  }
}
