package com.example.myapplication.data.source

import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.remote.api.ApiParams
import com.example.myapplication.data.repository.MovieRepository

class MoviePagingSource(private val repository: MovieRepository) : BasePagingSource<Movie>() {
    override suspend fun loadData(params: LoadParams<Int>): List<Movie>? {
        val hashMap = HashMap<String, String>()
        hashMap[ApiParams.PAGE] = (params.key ?: getFirstPage()).toString()
        return repository.getMovieList(hashMap).results
    }
}
