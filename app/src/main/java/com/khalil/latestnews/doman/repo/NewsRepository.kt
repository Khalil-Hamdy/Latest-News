package com.khalil.latestnews.doman.repo


import com.khalil.latestnews.doman.model.Article
import com.khalil.paymobtask.utils.ApiState
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun fetchNews(): Flow<ApiState<List<Article>>>
    fun getFavoriteArticles(): Flow<List<Article>>
    suspend fun toggleFavorite(article: Article)
    suspend fun isFavorite(publishedAt: String): Boolean
}