package com.example.myapplication.data.repository.impl

import com.example.myapplication.data.remote.api.ApiServices
import com.example.myapplication.data.remote.response.MovieListResponse
import com.example.myapplication.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class MovieRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {
    override suspend fun getMovieList(hashMap: HashMap<String, String>): MovieListResponse =
        withContext(ioDispatcher) {
            apiServices.getMovieList(hashMap)
        }
}
