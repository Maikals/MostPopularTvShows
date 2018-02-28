package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseFragment
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import kotlinx.android.synthetic.main.fragment_most_popular_tv_shows_list.*


class MostPopularTvShowsListFragment : BaseFragment(), MostPopularTvShowsListContract.View {

    private var presenter: MostPopularTvShowsListContract.Presenter? = null

    companion object {
        const val TAG = "PublicReposListFragment"

        fun newInstance(): MostPopularTvShowsListFragment = MostPopularTvShowsListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_most_popular_tv_shows_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setPresenter(presenter: MostPopularTvShowsListContract.Presenter) {
        this.presenter = presenter
    }

    override fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun getMostPopularTvShowsListOk(tvShowsList: List<TvShowListItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMostPopularTvShowsListKo(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
