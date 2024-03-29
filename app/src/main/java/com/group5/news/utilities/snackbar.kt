package com.group5.news.utilities

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

fun AppCompatActivity.makeSnackbar(message: String, duration: Int): Snackbar {
    return Snackbar.make(findViewById(android.R.id.content), message, duration)
}