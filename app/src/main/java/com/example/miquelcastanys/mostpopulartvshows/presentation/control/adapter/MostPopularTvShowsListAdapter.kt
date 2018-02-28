package com.example.miquelcastanys.mostpopulartvshows.presentation.control.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList.TvShowViewHolder


class MostPopularTvShowsListAdapter(private val tvShowList: List<TvShowListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return TvShowViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.tv_show_list_item, parent, false))
    }

    override fun getItemCount(): Int = tvShowList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? TvShowViewHolder)?.bindView(tvShowList[position])
    }
}