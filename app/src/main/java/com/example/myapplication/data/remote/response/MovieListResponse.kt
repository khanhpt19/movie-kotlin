package com.example.myapplication.data.remote.response

import com.example.myapplication.data.model.Movie
import com.squareup.moshi.Json

class MovieListResponse (
    @Json(name = "page") val page: Int? = null,
    @Json(name = "total_results") val totalResults: Int? = null,
    @Json(name = "total_page") val totalPage: Int? = null,
    @Json(name = "results") val results: List<Movie>? = null,
)