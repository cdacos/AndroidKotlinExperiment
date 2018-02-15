package com.cysmic.androidkotlinexperiment.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.cysmic.androidkotlinexperiment.R
import com.cysmic.androidkotlinexperiment.di.Injectable
import com.cysmic.androidkotlinexperiment.model.ConnectivityViewModel
import com.cysmic.androidkotlinexperiment.model.Story
import com.cysmic.androidkotlinexperiment.model.StoryListViewModel
import com.cysmic.androidkotlinexperiment.ourFormat
import kotlinx.android.synthetic.main.activity_story_list.*
import java.util.*
import javax.inject.Inject

class StoryListActivity : AppCompatActivity(), Injectable, StoryClickCallback {
  @Inject lateinit var viewModelProviderFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_story_list)

    val model = ViewModelProviders.of(this, viewModelProviderFactory).get(StoryListViewModel::class.java)

    val adapter = StoryListRecyclerViewAdapter(this)
    item_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    item_recycler.adapter = adapter

    // Alternative approach: Observer(adapter::addItems) where addItems takes `List<Story>?`
    model.getData().observe(this, Observer {
      if (it != null) {
        adapter.items = it
        setMessage(resources.getQuantityString(R.plurals.found_items, it.size, it.size))
      }
    })

    model.getMessage().observe(this, Observer {
      setMessage(it)
    })

    val connectivity = ViewModelProviders.of(this).get(ConnectivityViewModel::class.java)
    connectivity.getData().observe(this, Observer {
      when {
        it != true -> setMessage(resources.getString(R.string.online_warning))
        model.loadData() -> setMessage(resources.getString(R.string.fetching_items))
        else -> setMessage(resources.getString(R.string.online))
      }
    })

    swipe_container.setOnRefreshListener { model.reloadData() }
  }

  private fun setMessage(message: String?) {
    swipe_container.isRefreshing = false
    if (message?.isEmpty() != true) {
      story_list_message.text = resources.getString(R.string.message_format, Date().ourFormat(), message)
    }
  }

  override fun onStoryClick(story: Story) {
    val intent = Intent(this, StoryActivity::class.java)
    intent.putExtra("STORY_URL", story.url)
    intent.putExtra("STORY_TITLE", story.title)
    startActivity(intent)
  }

  override fun onStoryCommentsClick(story: Story) {
    val intent = Intent(this, StoryActivity::class.java)
    intent.putExtra("STORY_ID", story.id)
    intent.putExtra("STORY_TITLE", story.title)
    startActivity(intent)
  }
}