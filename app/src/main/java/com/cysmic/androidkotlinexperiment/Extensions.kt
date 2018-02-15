package com.cysmic.androidkotlinexperiment

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * I'm undecided on the virtues of extension functions. Static utils classes (without variable
 * state) ("pure" functions) are easily tested and self explaining.
 *
 * However, there is clearly a need to provide additional tools not included with the language.
 * I do appreciate the conciseness of extension functions, and used them happily (but sparingly)
 * in my C# projects too.
 *
 * This case provides a common way of formatting a Date as a String.
 */
fun Date.ourFormat(): String {
  return SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(this)
}