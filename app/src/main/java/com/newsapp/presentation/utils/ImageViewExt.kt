package com.newsapp.presentation.utils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.newsapp.R

fun ImageView.getImageFromUrl(url: String) {
    val uri: Uri = Uri.parse(url)

    val option: RequestOptions = RequestOptions()
        .placeholder(R.drawable.logo_shimmer)
        .error(R.drawable.twotone_report_gmailerrorred_24)

    Glide.with(this.context)
        .setDefaultRequestOptions(option)
        .load(uri)
        .fitCenter()
        .into(this)
}