package com.khalil.latestnews.data.mapper

import com.khalil.latestnews.data.datasource.remote.dto.ArticleDto
import com.khalil.latestnews.doman.model.Article

fun ArticleDto.toDomain(): Article {
    return Article(
        title = title,
        author = author ?: "Unknown",
        description = description ?: "",
        urlToImage = urlToImage ?: "",
        url = url,
        publishedAt = publishedAt,
        isFavorite = false
    )
}