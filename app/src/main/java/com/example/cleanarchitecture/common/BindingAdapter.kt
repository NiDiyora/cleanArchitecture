package com.example.cleanarchitecture.common

import android.media.Image
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cleanarchitecture.R

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, url: String?) {
    val optional = RequestOptions.placeholderOf(R.drawable.loading).error(R.drawable.error)
    Glide.with(view).load(url).into(view)
}