package com.khalil.latestnews.data.datasource.remote.dto

data class ArticleDto(
    val author: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)