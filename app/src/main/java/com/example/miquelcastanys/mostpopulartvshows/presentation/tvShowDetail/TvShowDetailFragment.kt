package com.example.miquelcastanys.mostpopulartvshows.presentation.tvShowDetail


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseFragment
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.control.adapter.SimilarTvShowsListAdapter
import com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces.OnListItemClickListener
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowDetail
import kotlinx.android.synthetic.main.fragment_tv_show_detail.*


class TvShowDetailFragment : BaseFragment(),
        TvShowDetailContract.View, OnListItemClickListener.View {

    private var presenter: TvShowDetailContract.Presenter? = null
    private var similarTvShowsListAdapter: SimilarTvShowsListAdapter? = null
    private val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,
            false)

    companion object {
        const val TAG = "TvShowDetailFragment"
        private const val TV_SHOW_DETAIL_ID_EXTRA = "tvShowDetailIdExtra"
        fun newInstance(id: Int): TvShowDetailFragment {
            val tvShowDetailFragment = TvShowDetailFragment()
            val arguments = Bundle()
            arguments.putInt(TV_SHOW_DETAIL_ID_EXTRA, id)

            tvShowDetailFragment.arguments = arguments
            return tvShowDetailFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_tv_show_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.start()
        setRecyclerView()
        setMoreOnClickListener()
    }

    private fun setMoreOnClickListener() {
        more.setOnClickListener{
            presenter?.openSimilarTvShowCompleteList()
        }
    }

    private fun setRecyclerView() {
        similarTvShowListRV.layoutManager = linearLayoutManager
        similarTvShowListRV.setHasFixedSize(true)
        similarTvShowListRV.isNestedScrollingEnabled = false

    }

    override fun setPresenter(presenter: TvShowDetailContract.Presenter) {
        this.presenter = presenter
    }

    override fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun getTvShowDetailOk(tvShowDetail: TvShowDetail) {
        infoContainer?.visibility = View.VISIBLE
        overview?.text = tvShowDetail.overview
        val ratingString = "${getString(R.string.rating)} ${tvShowDetail.rating}"
        tvShowRating?.text = ratingString
    }

    override fun getTvShowDetailKo(errorCode: String) {
        Toast.makeText(activity, getString(R.string.tv_show_detail_error), Toast.LENGTH_SHORT).show()
        activity.finish()
    }

    override fun getSimilarTvShowsListOk(tvShowList: List<BaseListItem>) {
        if (similarTvShowsListAdapter == null) {
            similarTvShowsListAdapter = SimilarTvShowsListAdapter(tvShowList, this)
            similarTvShowListRV.adapter = similarTvShowsListAdapter
        }
        similarTvShowsListAdapter?.notifyDataSetChanged()
    }

    override fun getSimilarTvShowListKo(errorCode: String) {
    }

    override fun onItemClick(position: Int, view: View) {
        presenter?.openTvShowDetail(position, view)
    }

}
