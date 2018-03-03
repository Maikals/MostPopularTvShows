package com.example.miquelcastanys.mostpopulartvshows

import com.example.miquelcastanys.mostpopulartvshows.domain.api.MostPopularTvShowsService
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowListResponse
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
class SimilarTvShowList {
    private val service = MostPopularTvShowsService.getService()


    @Test
    fun getMostPopularTvShowList() {
        val expectedResponse = Gson().fromJson(TestConstants.expectedTvShowListJson, TvShowListResponse::class.java)
        println("expectedResponse -> $expectedResponse")
        val tvShowDetail = runBlocking { service.getSimilarTvShowsList(1418,"98d3f21f52adf59ccbf65cb76683d73b", "en-US", 1).await() }
        println("tvShowDetail -> $tvShowDetail")
        assert(expectedResponse == tvShowDetail)
    }

    @Test
    fun getMostPopularTvShowListError() {
        try {
            runBlocking { service.getSimilarTvShowsList(1418,"", "en-US", 1).await() }
        } catch (e: HttpException) {
            assert(e.code() == 401)
        }
    }
}
