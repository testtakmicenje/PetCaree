package com.example.petcare.forum.utils

import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

fun Context.createShortToast(message: String) {

    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

fun <T> Context.openActivity(c: Class<T>) {

    this.startActivity(Intent(this, c))

}

fun createProgressBar(context: Context, container: ViewGroup): ProgressBar {

    val progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleLarge)

    container.addView(progressBar)

    return progressBar

}
