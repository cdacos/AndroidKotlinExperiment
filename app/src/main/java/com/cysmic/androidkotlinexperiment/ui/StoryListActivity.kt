package com.cysmic.androidkotlinexperiment.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.cysmic.androidkotlinexperiment.R
import com.cysmic.androidkotlinexperiment.di.Injectable
import com.cysmic.androidkotlinexperiment.model.ConnectivityViewModel
import com.cysmic.androidkotlinexperiment.model.StoryListViewModel
import kotlinx.android.synthetic.main.activity_story_list.*
import javax.inject.Inject

class StoryListActivity : AppCompatActivity(), Injectable {
  @Inject lateinit var viewModelProviderFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_story_list)

    val model = ViewModelProviders.of(this, viewModelProviderFactory).get(StoryListViewModel::class.java)

    val adapter = StoryListRecyclerViewAdapter()
    item_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    item_recycler.adapter = adapter

    // Alternative approach: Observer(adapter::addItems) where addItems takes `List<Story>?`
    model.getData().observe(this, Observer {
      if (it != null) {
        adapter.items = it
        story_list_message.text = resources.getQuantityString(R.plurals.found_items, it.size, it.size)
      }
    })

    val connectivity = ViewModelProviders.of(this).get(ConnectivityViewModel::class.java)
    connectivity.getData().observe(this, Observer {
      when {
        it != true -> story_list_message.setText(R.string.online_warning)
        model.loadData() -> story_list_message.setText(R.string.fetching_items)
        else -> story_list_message.setText(R.string.fetching_items)
      }
    })
  }
}