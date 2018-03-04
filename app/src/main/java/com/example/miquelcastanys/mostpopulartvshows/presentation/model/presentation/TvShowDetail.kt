package com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation

import android.os.Parcel
import android.os.Parcelable


data class TvShowDetail(val name: String,
                        val overview: String,
                        val bigImage: String,
                        val posterImage: String,
                        val rating: Double) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(overview)
        writeString(bigImage)
        writeString(posterImage)
        writeDouble(rating)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TvShowDetail> = object : Parcelable.Creator<TvShowDetail> {
            override fun createFromParcel(source: Parcel): TvShowDetail = TvShowDetail(source)
            override fun newArray(size: Int): Array<TvShowDetail?> = arrayOfNulls(size)
        }
    }
}