package com.example.myapplication.ui.home

import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository) : BaseViewModel() {
    private val _movieList = MutableStateFlow<List<Movie>>(emptyList())
    val movieList = _movieList.asStateFlow()

    fun getMovieList() {
        if (_movieList.value.isNotEmpty()) return
        viewModelScope.launch {
            try {
                _movieList.emit(repository.getMovieList().results ?: emptyList())
            } catch (e: Throwable) {
                onError(e)
            }
        }
    }
}
