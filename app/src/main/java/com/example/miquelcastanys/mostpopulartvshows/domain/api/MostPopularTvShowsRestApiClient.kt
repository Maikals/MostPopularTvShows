package com.example.miquelcastanys.mostpopulartvshows.domain.api

import com.example.miquelcastanys.mostpopulartvshows.domain.DomainConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MostPopularTvShowsRestApiClient {

    fun <S> createService(serviceClass: Class<S>): S {
        val httpClient = OkHttpClient.Builder()
                .connectTimeout(DomainConstants.TIMEOUT_CONNECTION_VALUE, TimeUnit.SECONDS)
                .readTimeout(DomainConstants.TIMEOUT_READ_VALUE, TimeUnit.SECONDS)
                .writeTimeout(DomainConstants.TIMEOUT_WRITE_VALUE, TimeUnit.SECONDS)
        val builder = Retrofit.Builder()
                .baseUrl(DomainConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
        return builder.client(httpClient.build()).build().create(serviceClass)
    }

}