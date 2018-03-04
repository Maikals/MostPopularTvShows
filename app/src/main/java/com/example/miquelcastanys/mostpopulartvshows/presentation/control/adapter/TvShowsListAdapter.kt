package com.example.miquelcastanys.mostpopulartvshows.presentation.control.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseFooterViewHolder
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseTvShowListAdapter
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseTvShowViewHolder
import com.example.miquelcastanys.mostpopulartvshows.presentation.genericHolders.FooterViewHolder
import com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces.OnListItemClickListener
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList.TvShowViewHolder


class TvShowsListAdapter(tvShowList: List<BaseListItem>,
                         listener: OnListItemClickListener.View) :
        BaseTvShowListAdapter(tvShowList, listener) {
    override fun bindView(holder: BaseTvShowViewHolder, tvShowListItem: TvShowListItem) {
        (holder as? TvShowViewHolder)?.bindView(tvShowListItem)
    }

    override fun createFooterViewHolder(parent: ViewGroup?): BaseFooterViewHolder =
            FooterViewHolder(LayoutInflater.from(parent?.context)
                    .inflate(R.layout.footer_list_item, parent, false))

    override fun createTvShowViewHolder(parent: ViewGroup?): BaseTvShowViewHolder =
            TvShowViewHolder(LayoutInflater.from(parent?.context)
                    .inflate(R.layout.tv_show_list_item, parent, false), this)

}