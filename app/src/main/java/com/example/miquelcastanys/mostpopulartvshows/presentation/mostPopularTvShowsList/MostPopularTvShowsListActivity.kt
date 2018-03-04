package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseActivityList

class MostPopularTvShowsListActivity : BaseActivityList() {

    companion object {
        const val TAG: String = "MostPopularTvShowsListActivity"
    }

    override fun setToolbarTitle() {
        supportActionBar?.setTitle(R.string.most_popular_tv_shows_title)
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
