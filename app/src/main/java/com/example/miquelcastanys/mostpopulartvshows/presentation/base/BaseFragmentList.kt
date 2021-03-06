package com.example.miquelcastanys.mostpopulartvshows.presentation.base

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.control.adapter.TvShowsListAdapter
import com.example.miquelcastanys.mostpopulartvshows.presentation.enumeration.EmptyViewModel
import com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces.OnListItemClickListener
import com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList.TvShowsListContract
import kotlinx.android.synthetic.main.fragment_most_popular_tv_shows_list.*


abstract class BaseFragmentList : BaseFragment(), TvShowsListContract.View, OnListItemClickListener.View {

    private var presenter: TvShowsListContract.Presenter? = null
    private var tvShowsListAdapter: TvShowsListAdapter? = null


    private val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,
            false)
    private var loading: Boolean = false

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            if (dy > 0) {
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()
                if (!presenter?.isLastPage!! && !loading) {
                    if (visibleItemCount + pastVisibleItems >= totalItemCount - 5) {
                        loading = true
                        presenter?.getTvShowsList()
                    }
                }
            } else if (linearLayoutManager.findLastVisibleItemPosition() == tvShowsListRV.adapter.itemCount - 1
                    && !presenter?.isLastPage!!) {
                presenter?.getTvShowsList()
                loading = true
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_most_popular_tv_shows_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setRefreshLayoutBehaviour()
        presenter?.start()
    }

    private fun setRefreshLayoutBehaviour() {
        mostPopularTvShowsListRefreshLayout.setOnRefreshListener {
            (tvShowsListRV.adapter as? TvShowsListAdapter)?.restartLastPosition()
            presenter?.start()
        }
    }

    private fun setRecyclerView() {
        tvShowsListRV.layoutManager = linearLayoutManager
        attachScrollListener()

    }

    private fun initializeRecyclerViewAdapter(tvShowsList: List<BaseListItem>) {
        if (tvShowsListAdapter == null) {
            tvShowsListAdapter = TvShowsListAdapter(tvShowsList, this)
            tvShowsListRV.adapter = tvShowsListAdapter
        }
    }

    override fun getTvShowsListOk(tvShowsList: List<BaseListItem>) {
        loading = false
        if (!tvShowsList.isEmpty()) {
            attachScrollListener()
            hideEmptyView()
            showRecyclerView()
            initializeRecyclerViewAdapter(tvShowsList)
        } else {
            emptyView.fillViews(EmptyViewModel.EMPTY)
            hideRecyclerView()
            showEmptyView()
        }
        tvShowsListAdapter?.notifyDataSetChanged()
    }


    override fun getTvShowsListKo(errorMessage: String) {
        loading = false
        emptyView.fillViews(EmptyViewModel.ERROR)
        hideRecyclerView()
        showEmptyView()
    }

    private fun attachScrollListener() {
        tvShowsListRV.addOnScrollListener(onScrollListener)
    }

    override fun setPresenter(presenter: TvShowsListContract.Presenter) {
        this.presenter = presenter
    }


    override fun showProgressBar(show: Boolean) {
        if (mostPopularTvShowsListRefreshLayout.isRefreshing) {
            if (!show)
                mostPopularTvShowsListRefreshLayout.isRefreshing = false
        } else progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showRecyclerView() {
        tvShowsListRV.visibility = View.VISIBLE
    }

    private fun hideRecyclerView() {
        tvShowsListRV.visibility = View.GONE
    }

    private fun hideEmptyView() {
        emptyView.visibility = View.GONE

    }

    private fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unattachScrollListener()
    }

    private fun unattachScrollListener() {
        tvShowsListRV.removeOnScrollListener(onScrollListener)
    }

    override fun onDetach() {
        presenter?.detach()
        super.onDetach()
    }

    override fun onItemClick(position: Int, view: View) {
        presenter?.openTvShowDetail(position, view)
    }
}