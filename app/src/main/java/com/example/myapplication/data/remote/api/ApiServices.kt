package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiServices {
    @GET(ApiConstant.MOVIE_LIST)
    suspend fun getMovieList(@QueryMap hashMap: HashMap<String, String> = HashMap()): MovieListResponse
}
