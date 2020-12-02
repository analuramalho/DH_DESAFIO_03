package com.example.digitalhousemarvelapp.home.repository

import com.example.digitalhousemarvelapp.data.api.NetworkUtils
import com.example.digitalhousemarvelapp.data.model.ResponseModel
import com.example.digitalhousemarvelapp.home.model.ComicModel
import retrofit2.http.GET
import retrofit2.http.Query

interface IComicEndpoint {
    @GET("/v1/public/series/24291/comics")
    suspend fun getComics(@Query("titleStartsWith") titleStartsWith: String?, @Query("offset") offset: Int? = 0): ResponseModel<ComicModel>

    companion object {
        val endpoint: IComicEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IComicEndpoint::class.java)
        }
    }
}