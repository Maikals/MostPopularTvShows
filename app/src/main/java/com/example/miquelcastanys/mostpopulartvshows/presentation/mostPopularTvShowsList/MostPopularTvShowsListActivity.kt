package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_most_popular_tv_shows_list.*

class MostPopularTvShowsListActivity : BaseActivity() {

    companion object {
        const val TAG: String = "MostPopularTvShowsListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_most_popular_tv_shows_list)
        initializeFragment(savedInstanceState)
        setToolbar()
        setFragment()
    }


    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    override fun createFragment() {
        currentFragment = MostPopularTvShowsListFragment.newInstance()
        val mostPopularTvShowsListPresenter = MostPopularTvShowsListPresenter()
        mostPopularTvShowsListPresenter.attach(this,
                currentFragment as MostPopularTvShowsListFragment,
                MostPopularTvShowsSourceImpl())
        (currentFragment as MostPopularTvShowsListFragment).setPresenter(mostPopularTvShowsListPresenter)
        currentTag = MostPopularTvShowsListFragment.TAG
    }
}
