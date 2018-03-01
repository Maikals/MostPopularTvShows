package com.example.miquelcastanys.mostpopulartvshows.presentation.control.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.genericHolders.FooterViewHolder
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList.TvShowViewHolder


class MostPopularTvShowsListAdapter(private val tvShowList: List<BaseListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TV_SHOW_TYPE: Int = 0
        private const val FOOTER_TYPE: Int = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                TV_SHOW_TYPE -> TvShowViewHolder(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.tv_show_list_item, parent, false))
                else -> FooterViewHolder(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.footer_list_item, parent, false))
            }


    override fun getItemCount(): Int = tvShowList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? TvShowViewHolder)?.bindView((tvShowList[position] as? TvShowListItem)!!)
    }


    override fun getItemViewType(position: Int): Int =
            if (tvShowList[position] is TvShowListItem) TV_SHOW_TYPE
            else FOOTER_TYPE
}