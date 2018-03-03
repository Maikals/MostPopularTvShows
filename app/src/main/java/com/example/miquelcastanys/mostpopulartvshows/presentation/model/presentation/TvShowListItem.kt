package com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation

import android.os.Parcel
import android.os.Parcelable
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem


data class TvShowListItem(val id: Int,
                          val title: String,
                          val voteAverage: Double,
                          val image: String,
                          val overview: String) : BaseListItem()