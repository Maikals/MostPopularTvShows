package com.example.miquelcastanys.mostpopulartvshows.presentation.base

import android.os.Bundle
import com.example.miquelcastanys.mostpopulartvshows.R
import kotlinx.android.synthetic.main.activity_most_popular_tv_shows_list.*


abstract class BaseActivityList : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_most_popular_tv_shows_list)
        initializeFragment(savedInstanceState)
        setToolbar()
        setFragment()
    }


    private fun setToolbar() {
        setSupportActionBar(toolbar)
        setToolbarTitle()
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    abstract fun setToolbarTitle()

}