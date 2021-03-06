package com.example.digitalhousemarvelapp.comic.repository

import com.example.digitalhousemarvelapp.data.api.NetworkUtils
import com.example.digitalhousemarvelapp.data.model.ResponseModel
import com.example.digitalhousemarvelapp.comic.model.ComicModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IComicEndpoint {
    @GET("/v1/public/series/784/comics")
    suspend fun getComics(@Query("titleStartsWith") titleStartsWith: String?, @Query("offset") offset: Int? = 0): ResponseModel<ComicModel>
    @GET("/v1/public/comics/{comicId}")
    suspend fun getComicById(@Path("comicId") comicId: Int): ResponseModel<ComicModel>

    companion object {
        val endpoint: IComicEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IComicEndpoint::class.java)
        }
    }
}