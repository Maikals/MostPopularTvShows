package com.example.miquelcastanys.mostpopulartvshows.presentation.tvShowDetail

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.transition.Slide
import android.view.MenuItem
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseActivity
import com.example.miquelcastanys.mostpopulartvshows.presentation.glideModule.GlideApp
import kotlinx.android.synthetic.main.activity_tv_show_detail.*


class TvShowDetailActivity : BaseActivity() {

    companion object {
        private const val TAG = "TvShowDetailActivity"
        const val TV_SHOW_DETAIL_ID_EXTRA = "tvShowDetailIdExtra"
        const val TV_SHOW_DETAIL_BIG_IMAGE_EXTRA = "tvShowDetailBigImageExtra"
        const val TV_SHOW_DETAIL_TITLE_EXTRA = "tvShowDetailTitleExtra"
        const val TV_SHOW_DETAIL_OVERVIEW_EXTRA = "tvShowDetailOverviewExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) initActivityTransitions()
        setContentView(R.layout.activity_tv_show_detail)
        val (imgExtra, title) = recoverDataFromIntent()
        setToolbar(title)
        setToolbarImage(imgExtra)
        initializeFragment(savedInstanceState)
        setFragment()
    }


    private fun setToolbarImage(imgExtra: String) {
        GlideApp
                .with(this)
                .load("https://image.tmdb.org/t/p/w1280$imgExtra")
                .centerCrop()
                .into(tvShowBackdrop)
    }

    private fun setToolbar(title: String) {
        collapsingToolbar.title = title
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    private fun recoverDataFromIntent(): Pair<String, String> {
        val imgExtra = intent.getStringExtra(TV_SHOW_DETAIL_BIG_IMAGE_EXTRA)
        val title = intent.getStringExtra(TV_SHOW_DETAIL_TITLE_EXTRA)
        return Pair(imgExtra, title)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initActivityTransitions() {
        val transition = Slide()
        transition.excludeTarget(android.R.id.statusBarBackground, true)
        window.enterTransition = transition
        window.returnTransition = transition
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun createFragment() {
        val tvShowDetailFragment = TvShowDetailFragment.newInstance(
                intent.getIntExtra(TV_SHOW_DETAIL_ID_EXTRA, 0),
                intent.getStringExtra(TV_SHOW_DETAIL_OVERVIEW_EXTRA))
        val presenter = TvShowDetailPresenter(intent.getIntExtra(TV_SHOW_DETAIL_ID_EXTRA, -1))
        presenter.attach(this, tvShowDetailFragment, MostPopularTvShowsSourceImpl())
        tvShowDetailFragment.setPresenter(presenter)
        currentFragment = tvShowDetailFragment
        currentTag = TvShowDetailFragment.TAG
    }
}
