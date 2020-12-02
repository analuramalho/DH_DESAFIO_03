package com.example.digitalhousemarvelapp.home.model

import android.media.Image
import com.example.digitalhousemarvelapp.data.model.ThumbnailModel
import com.example.digitalhousemarvelapp.home.model.ComicDate
import com.example.digitalhousemarvelapp.home.model.ComicPrice

data class ComicModel(
    val id : Int,
    val issueNumber: String,
    val title:String,
    val description:String,
    val pageCount :Int,
    val dates:List<ComicDate>,
    val prices:List<ComicPrice>,
    val thumbnail:ThumbnailModel?,
    val images: List<ThumbnailModel>?
)