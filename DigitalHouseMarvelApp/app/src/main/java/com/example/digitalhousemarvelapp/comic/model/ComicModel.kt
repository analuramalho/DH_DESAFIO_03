package com.example.digitalhousemarvelapp.comic.model

import com.example.digitalhousemarvelapp.data.model.ThumbnailModel

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