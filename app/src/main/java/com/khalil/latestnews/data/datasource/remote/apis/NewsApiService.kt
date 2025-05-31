package com.khalil.latestnews.data.datasource.remote.apis

import com.khalil.latestnews.data.datasource.remote.dto.LatestNewsResponse
import com.khalil.paymobtask.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v1/articles")
    suspend fun getNews(
        @Query("source") source: String = "the-next-web",
        @Query("apiKey") apiKey: String = Constants.APIKEY
    ): LatestNewsResponse
}