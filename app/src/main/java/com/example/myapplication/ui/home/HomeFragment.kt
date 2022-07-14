package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.exa.HomeAdapter
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private val movieAdapter = HomeAdapter(itemClickListener = {
        Timber.d("amen, $it")
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            getMovieList()
        }
        if (viewBinding.recyclerViewMovieList.adapter == null) {
            viewBinding.recyclerViewMovieList.adapter = movieAdapter
        }
        lifecycleScope.launchWhenStarted {
            viewModel.movieList.collectLatest { movieList ->
                movieAdapter.submitList(movieList)
            }
        }
    }
}
