package com.example.miquelcastanys.mostpopulartvshows.presentation.control.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseFooterViewHolder
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseTvShowListAdapter
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseTvShowViewHolder
import com.example.miquelcastanys.mostpopulartvshows.presentation.genericHolders.FooterViewHolder
import com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces.OnListItemClickListener
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.tvShowDetail.SimilarTvShowViewHolder


class SimilarTvShowsListAdapter(tvShowList: List<BaseListItem>
                                , listener: OnListItemClickListener.View)
    : BaseTvShowListAdapter(tvShowList, listener) {
    override fun bindView(holder: BaseTvShowViewHolder, tvShowListItem: TvShowListItem) {
        (holder as? SimilarTvShowViewHolder)?.bindView(tvShowListItem)
    }

    override fun createFooterViewHolder(parent: ViewGroup?): BaseFooterViewHolder =
            FooterViewHolder(LayoutInflater.from(parent?.context)
                    .inflate(R.layout.similar_tv_show_last_item, parent, false))


    override fun createTvShowViewHolder(parent: ViewGroup?): BaseTvShowViewHolder =
            SimilarTvShowViewHolder(LayoutInflater.from(parent?.context)
                    .inflate(R.layout.similar_tv_show_list_item, parent, false), this)


    override fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_in_right)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

}
