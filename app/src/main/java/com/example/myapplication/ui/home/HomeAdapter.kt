package com.example.myapplication.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.R
import com.example.myapplication.base.BasePagingAdapter
import com.example.myapplication.data.model.Movie
import com.example.myapplication.databinding.ItemMovieBinding
import com.example.myapplication.utils.setSingleClick

class HomeAdapter(val itemClickListener: (Movie) -> Unit = {}) :
    BasePagingAdapter<Movie, ItemMovieBinding>(
        object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_movie
    override fun bindFirstTime(binding: ItemMovieBinding) {
        binding.apply {
            root.setSingleClick {
                item?.apply {
                    itemClickListener(this)
                }
            }
        }
    }
}
