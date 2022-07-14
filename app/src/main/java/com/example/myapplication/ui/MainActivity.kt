package com.example.myapplication.ui

import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    override val viewModel: BaseViewModel by viewModels()
    override val layoutId: Int = R.layout.activity_main
}
