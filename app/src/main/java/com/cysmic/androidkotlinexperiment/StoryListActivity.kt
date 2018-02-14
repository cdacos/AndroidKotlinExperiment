package com.cysmic.androidkotlinexperiment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.cysmic.androidkotlinexperiment.di.Injectable
import com.cysmic.androidkotlinexperiment.model.ConnectivityViewModel
import com.cysmic.androidkotlinexperiment.model.StoryListViewModel
import com.cysmic.androidkotlinexperiment.ui.StoryRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_story_list.*
import javax.inject.Inject

class StoryListActivity : AppCompatActivity(), Injectable {
  @Inject lateinit var viewModelProviderFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_story_list)

    val model = ViewModelProviders.of(this, viewModelProviderFactory).get(StoryListViewModel::class.java)

    item_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    item_recycler.adapter = StoryRecyclerViewAdapter(model)

    model.getData().observe(this, Observer {
      item_recycler.adapter.notifyDataSetChanged()
    })

    val connectivity = ViewModelProviders.of(this).get(ConnectivityViewModel::class.java)
    connectivity.getData().observe(this, Observer {
      when {
        (it != true) -> story_list_message.setText(R.string.online_warning)
        model.loadData() -> story_list_message.setText(R.string.fetching_items)
        else -> story_list_message.setText(R.string.fetching_items)
      }
    })
  }
}