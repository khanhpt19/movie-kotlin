package com.example.myapplication.ui.home

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.base.BasePagingAdapter
import com.example.myapplication.base.BasePagingFragment
import com.example.myapplication.data.model.Movie
import com.example.myapplication.databinding.FragmentPagingBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BasePagingFragment<FragmentPagingBinding, HomeViewModel, Movie>() {
    override val viewModel: HomeViewModel by viewModels()
    override val pagingAdapter: BasePagingAdapter<Movie, out ViewDataBinding> by lazy {
        HomeAdapter(
            itemClickListener = {
                Timber.d("amen $it")
            }
        )
    }
    override val swipeRefreshLayout: SwipeRefreshLayout
        get() = viewBinding.refreshLayout
    override val recyclerView: RecyclerView
        get() = viewBinding.recyclerView

}