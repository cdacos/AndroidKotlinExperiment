package com.cysmic.androidkotlinexperiment.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.cysmic.androidkotlinexperiment.R
import kotlinx.android.synthetic.main.activity_story.*

class StoryActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_story)

    val actionBar = supportActionBar
    if (actionBar != null) {
      actionBar.subtitle = intent.getStringExtra("STORY_TITLE")
      actionBar.setDisplayHomeAsUpEnabled(true)
    }

    webview.loadUrl(intent.getStringExtra("STORY_URL"))
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == android.R.id.home) finish()
    return super.onOptionsItemSelected(item)
  }
}
