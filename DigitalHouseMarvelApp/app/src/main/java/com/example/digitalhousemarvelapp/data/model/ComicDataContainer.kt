package com.example.digitalhousemarvelapp.data.model

import com.example.digitalhousemarvelapp.home.model.ComicModel

data class ComicDataContainer (
    val offset:Int,
    val limit:Int,
    val total:Int,
    val count:Int,
    val results:List<ComicModel>
)