package com.example.miquelcastanys.mostpopulartvshows.presentation.tvShowDetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseFragment
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowDetail
import kotlinx.android.synthetic.main.fragment_tv_show_detail.*


/**
 * A simple [Fragment] subclass.
 */
class TvShowDetailFragment : BaseFragment(), TvShowDetailContract.View {

    private var presenter: TvShowDetailContract.Presenter? = null
    companion object {
        const val TAG = "TvShowDetailFragment"
        private const val TV_SHOW_DETAIL_ID_EXTRA = "tvShowDetailIdExtra"
        private const val TV_SHOW_DETAIL_OVERVIEW_EXTRA = "tvShowDetailOverviewExtra"
        fun newInstance(id: Int, overview: String) : TvShowDetailFragment {
            val tvShowDetailFragment = TvShowDetailFragment()
            val arguments = Bundle()
            arguments.putInt(TV_SHOW_DETAIL_ID_EXTRA, id)
            arguments.putString(TV_SHOW_DETAIL_OVERVIEW_EXTRA, overview)
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
        overview.text = arguments.getString(TV_SHOW_DETAIL_OVERVIEW_EXTRA)
        presenter?.start()
    }

    override fun setPresenter(presenter: TvShowDetailContract.Presenter) {
        this.presenter = presenter
    }

    override fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun getTvShowDetailOk(tvShowDetail: TvShowDetail) {
        infoContainer.visibility = View.VISIBLE
        overview.text = tvShowDetail.overview
        tvShowRating.text = tvShowDetail.rating.toString()
    }

    override fun getTvShowDetailKo(errorCode: String) {
        Toast.makeText(activity, getString(R.string.tv_show_detail_error), Toast.LENGTH_SHORT).show()
        activity.finish()
    }

}
