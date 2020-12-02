package com.example.digitalhousemarvelapp.comic.repository

class ComicRepository() {
    private val _client = IComicEndpoint.endpoint
    suspend fun getComics(title: String?, offset: Int? = 0) = _client.getComics(title, offset)
    suspend fun getComicById(comicId:Int) = _client.getComicById(comicId)
}