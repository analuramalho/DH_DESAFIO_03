package com.example.digitalhousemarvelapp.home.repository

import android.content.Context
import com.example.digitalhousemarvelapp.R

class ComicRepository() {
    private val _client = IComicEndpoint.endpoint
    suspend fun getComics(title: String?, offset: Int? = 0) = _client.getComics(title, offset)
}