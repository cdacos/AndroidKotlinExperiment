package com.cysmic.androidkotlinexperiment.model

data class Story(
    val id: String,
    val title: String,
    val by: String,
    val descendants: Int,
    val score: Int,
    val time: Long,
    val url: String,
    val type: String,
    val text: String?)