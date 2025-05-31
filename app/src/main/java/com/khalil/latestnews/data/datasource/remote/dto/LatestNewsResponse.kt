package com.khalil.latestnews.data.datasource.remote.dto

data class LatestNewsResponse(
    val articles: List<ArticleDto>,
    val sortBy: String? = null,
    val source: String? = null,
    val status: String? = null
)