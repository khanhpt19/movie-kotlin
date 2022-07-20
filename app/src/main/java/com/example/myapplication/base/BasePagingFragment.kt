package com.example.myapplication.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BasePagingFragment<ViewBinding : ViewDataBinding, ViewModel : BasePagingViewModel<Item>, Item : Any> :
    BaseFragment<ViewBinding, ViewModel>() {

    override val layoutId: Int = R.layout.fragment_paging
    abstract val pagingAdapter: BasePagingAdapter<Item, out ViewDataBinding>
    abstract val swipeRefreshLayout: SwipeRefreshLayout?
    abstract val recyclerView: RecyclerView?

    open fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPaging()
    }

    /**
     * setup paging
     */
    private fun setupPaging() {
        swipeRefreshLayout?.setOnRefreshListener {
            viewModel.doRefresh()
        }
        recyclerView?.layoutManager = getLayoutManager()
        recyclerView?.adapter = pagingAdapter
        recyclerView?.setHasFixedSize(true)
        lifecycleScope.launch {
            viewModel.items.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
        pagingAdapter.addLoadStateListener {
            viewModel.handleLoadStates(
                combinedLoadStates = it,
                itemCount = pagingAdapter.itemCount
            )
        }
    }

    override fun handleLoading(isLoading: Boolean) {
        // use progress bar
    }
}