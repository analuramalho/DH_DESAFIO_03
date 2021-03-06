package com.example.digitalhousemarvelapp.data.model

data class ResponseModel<T> (
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: DataModel<T>
)
