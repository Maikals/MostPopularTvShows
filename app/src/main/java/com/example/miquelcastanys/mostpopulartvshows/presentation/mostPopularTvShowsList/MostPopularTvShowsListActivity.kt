package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_most_popular_tv_shows_list.*

class MostPopularTvShowsListActivity : BaseActivity() {

    private var currentFragment: Fragment? = null
    private var currentTag: String? = null

    companion object {
        const val TAG: String = "MostPopularTvShowsListActivity"
        private const val CURRENT_FRAGMENT_TAG: String = "currentTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_most_popular_tv_shows_list)
        initializeFragment(savedInstanceState)
        setToolbar()
        setFragment()
    }

    private fun initializeFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null || !savedInstanceState.containsKey(currentTag)) {
            createFragment()
        } else {
            currentTag = savedInstanceState.getString(CURRENT_FRAGMENT_TAG)
            currentFragment = supportFragmentManager.getFragment(savedInstanceState, currentTag)
        }
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun createFragment() {
        //TODO add MostPopularTvShowsListFragment
    }

    private fun setFragment() =
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, currentFragment, currentTag).commit()
}
