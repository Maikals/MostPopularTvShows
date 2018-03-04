package com.example.miquelcastanys.mostpopulartvshows

import com.example.miquelcastanys.mostpopulartvshows.domain.api.MostPopularTvShowsService
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowDetailResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.util.PresentationConstants
import com.google.gson.Gson
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import retrofit2.HttpException
import ru.gildor.coroutines.retrofit.await

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TvShowDetail {
    private val service = MostPopularTvShowsService.getService()


    @Test
    fun getMostPopularTvShowList() {
        val expectedResponse = Gson().fromJson(TestConstants.expectedTvShowDetailJson, TvShowDetailResponse::class.java)
        println("expectedResponse -> $expectedResponse")
        val tvShowsList = runBlocking { service.getTvShowDetail(TestConstants.TV_SHOW_ID, PresentationConstants.API_KEY, PresentationConstants.API_KEY).await() }
        println("tvShowsList -> $tvShowsList")
        assert(expectedResponse == tvShowsList)
    }

    @Test
    fun getMostPopularTvShowListError() {
        try {
            runBlocking { service.getTvShowDetail(TestConstants.TV_SHOW_ID, "", PresentationConstants.API_KEY).await() }
        } catch (e: HttpException) {
            assert(e.code() == 401)
        }
    }
}
