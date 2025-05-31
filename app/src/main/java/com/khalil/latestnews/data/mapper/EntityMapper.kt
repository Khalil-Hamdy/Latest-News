package com.khalil.latestnews.data.mapper

import com.khalil.latestnews.data.datasource.local.entity.FavoriteArticleEntity
import com.khalil.latestnews.doman.model.Article

fun FavoriteArticleEntity.toDomain(): Article {
    return Article(
        title = title,
        author = author,
        description = description,
        urlToImage = urlToImage,
        url = url,
        publishedAt = publishedAt,
        isFavorite = true
    )
}

fun Article.toEntity(): FavoriteArticleEntity {
    return FavoriteArticleEntity(
        title = title,
        author = author,
        description = description,
        urlToImage = urlToImage,
        url = url,
        publishedAt = publishedAt
    )
}