package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseFragment
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.control.adapter.MostPopularTvShowsListAdapter
import kotlinx.android.synthetic.main.fragment_most_popular_tv_shows_list.*


class MostPopularTvShowsListFragment : BaseFragment(), MostPopularTvShowsListContract.View {

    private var presenter: MostPopularTvShowsListContract.Presenter? = null
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
                    if (visibleItemCount + pastVisibleItems >= totalItemCount - 3) {
                        loading = true
                        presenter?.getMostPopularTvShowsList()
                    }
                }
            } else if (linearLayoutManager.findLastVisibleItemPosition() == mostPopularTvShowsListRV.adapter.itemCount - 1
                    && !presenter?.isLastPage!!) {
                presenter?.getMostPopularTvShowsList()
                loading = true
            }
        }
    }

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
        setRecyclerView()
        setRefreshLayoutBehaviour()
        presenter?.start()
    }

    private fun setRefreshLayoutBehaviour() {
        mostPopularTvShowsListRefreshLayout.setOnRefreshListener {
            (mostPopularTvShowsListRV.adapter as? MostPopularTvShowsListAdapter)?.restartLastPosition()
            presenter?.start()
        }
    }

    private fun setRecyclerView() {
        mostPopularTvShowsListRV.layoutManager = linearLayoutManager
        mostPopularTvShowsListRV.addOnScrollListener(onScrollListener)

    }

    override fun setPresenter(presenter: MostPopularTvShowsListContract.Presenter) {
        this.presenter = presenter
    }

    override fun showProgressBar(show: Boolean) {
        if (mostPopularTvShowsListRefreshLayout.isRefreshing) {
            if (!show)
                mostPopularTvShowsListRefreshLayout.isRefreshing = false
        } else progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private var mostPopularTvShowsListAdapter: MostPopularTvShowsListAdapter? = null

    override fun getMostPopularTvShowsListOk(tvShowsList: List<BaseListItem>) {
        loading = false
        if (mostPopularTvShowsListAdapter == null) {
            mostPopularTvShowsListAdapter = MostPopularTvShowsListAdapter(tvShowsList)
            mostPopularTvShowsListRV.adapter = mostPopularTvShowsListAdapter
        }
        mostPopularTvShowsListAdapter?.notifyDataSetChanged()
        mostPopularTvShowsListRV.visibility = View.VISIBLE
    }

    override fun getMostPopularTvShowsListKo(errorMessage: String) {
        loading = false
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mostPopularTvShowsListRV.removeOnScrollListener(onScrollListener)
    }

    override fun onDetach() {
        presenter?.detach()
        super.onDetach()
    }

}