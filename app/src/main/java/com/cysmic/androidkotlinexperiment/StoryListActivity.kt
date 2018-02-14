package com.cysmic.androidkotlinexperiment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.cysmic.androidkotlinexperiment.di.Injectable
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
  }
}