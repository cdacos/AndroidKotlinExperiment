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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class StoryListRecyclerViewAdapter(private val onStoryClickCallback: StoryClickCallback) : RecyclerView.Adapter<StoryListRecyclerViewAdapter.StoryRecyclerViewHolder>() {
  private val df = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

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
    holder.view.setOnClickListener { onStoryClickCallback.onStoryClick(story) }
    holder.position.text = (position + 1).toString()
    holder.title.text = story.title
    val resources = holder.notes.context.resources
    val points = resources.getQuantityString(R.plurals.points, story.score, story.score)
    val date = df.format(Date(story.time*1000))
    val comments = resources.getQuantityString(R.plurals.comments, story.descendants, story.descendants)
    holder.notes.text = resources.getString(R.string.story_notes, points, date, comments)
  }

  override fun getItemCount(): Int {
    return items.size
  }

  class StoryRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val view = itemView
    val position: TextView = itemView.findViewById(R.id.position)
    val title: TextView = itemView.findViewById(R.id.title)
    val notes: TextView = itemView.findViewById(R.id.notes)
  }
}
