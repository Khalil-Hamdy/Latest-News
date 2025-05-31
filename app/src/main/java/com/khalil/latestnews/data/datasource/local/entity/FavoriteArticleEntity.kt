package com.khalil.latestnews.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_articles")
data class FavoriteArticleEntity(
    @PrimaryKey val publishedAt: String,
    val author: String,
    val description: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val isFavorite: Boolean = false
)