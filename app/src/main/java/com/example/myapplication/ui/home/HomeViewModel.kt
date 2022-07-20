package com.example.myapplication.ui.home

import com.example.myapplication.base.BasePagingViewModel
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.data.source.BasePagingSource
import com.example.myapplication.data.source.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository) :
    BasePagingViewModel<Movie>() {
    override fun createPagingSource(): BasePagingSource<Movie> {
        return MoviePagingSource(
            repository
        )
    }

}