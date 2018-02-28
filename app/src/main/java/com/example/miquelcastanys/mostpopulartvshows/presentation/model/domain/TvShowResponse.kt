package com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain


data class TvShowResponse(val original_name: String,
                          val genre_ids: List<Int>,
                          val name: String,
                          val popularity: Double,
                          val origin_country: List<String>,
                          val vote_count: Int,
                          val first_air_date: String,
                          val back_drop_path: String = "",
                          val original_language: String,
                          val id: String,
                          val overview: String,
                          val poster_path: String = "")