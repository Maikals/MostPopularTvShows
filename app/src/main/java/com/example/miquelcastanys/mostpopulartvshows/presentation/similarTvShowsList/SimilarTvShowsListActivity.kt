package com.example.miquelcastanys.mostpopulartvshows.presentation.similarTvShowsList

import android.os.Bundle
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseActivityList
import com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList.MostPopularTvShowsListFragment

class SimilarTvShowsListActivity : BaseActivityList() {

    private var title = ""
    private var id = -1

    companion object {
        const val TAG = "SimilarTvShowsListActivity"
        const val TV_SHOW_ID_EXTRA = "tvShowIdExtra"
        const val TV_SHOW_TITLE_EXTRA = "tvShowTitleExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getExtraData()
        super.onCreate(savedInstanceState)
    }

    private fun getExtraData() {
        title = intent.getStringExtra(TV_SHOW_TITLE_EXTRA)
        id = intent.getIntExtra(TV_SHOW_ID_EXTRA, -1)
    }

    override fun setToolbarTitle() {
        supportActionBar?.title = "${getString(R.string.similar_to)} $title"
    }

    override fun createFragment() {
        currentFragment = SimilarTvShowsListFragment.newInstance()
        val similarTvShowsListPresenter = SimilarTvShowsListPresenter(id)
        similarTvShowsListPresenter.attach(this,
                currentFragment as SimilarTvShowsListFragment,
                MostPopularTvShowsSourceImpl())
        (currentFragment as SimilarTvShowsListFragment).setPresenter(similarTvShowsListPresenter)
        currentTag = SimilarTvShowsListFragment.TAG
    }

    override fun onBackPressed() {
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        super.onBackPressed()
    }

}
