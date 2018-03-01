package com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation

import android.os.Parcel
import android.os.Parcelable
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem


data class TvShowListItem(val id: Int,
                          val title: String,
                          val voteAverage: Double,
                          val image: String) : BaseListItem(), Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readDouble(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(title)
        writeDouble(voteAverage)
        writeString(image)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TvShowListItem> = object : Parcelable.Creator<TvShowListItem> {
            override fun createFromParcel(source: Parcel): TvShowListItem = TvShowListItem(source)
            override fun newArray(size: Int): Array<TvShowListItem?> = arrayOfNulls(size)
        }
    }
}