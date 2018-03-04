package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList


import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseFragmentList
import com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces.OnListItemClickListener


class MostPopularTvShowsListFragment : BaseFragmentList(), OnListItemClickListener.View {

    companion object {
        const val TAG = "MostPopularTvShowsListFragment"

        fun newInstance(): MostPopularTvShowsListFragment = MostPopularTvShowsListFragment()
    }

}
