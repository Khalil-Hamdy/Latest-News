package com.khalil.latestnews.data.repoimpl

import android.util.Log
import com.khalil.latestnews.data.datasource.local.dao.FavoriteNewsDao
import com.khalil.latestnews.data.datasource.remote.apis.NewsApiService
import com.khalil.latestnews.data.mapper.toDomain
import com.khalil.latestnews.data.mapper.toEntity
import com.khalil.latestnews.doman.model.Article
import com.khalil.latestnews.doman.repo.NewsRepository
import com.khalil.paymobtask.utils.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// data/repository/NewsRepositoryImpl.kt
class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService,
    private val dao: FavoriteNewsDao
) : NewsRepository {

    override fun fetchNews(): Flow<ApiState<List<Article>>> = flow {
        emit(ApiState.Loading)
        try {
            val response = api.getNews()
            val articles = response.articles.map { it.toDomain() }
            val favoriteNews = try {
                dao.getFavorites().first()
            } catch (e: Exception) {
                emptyList()
            }
            val favoriteIds = favoriteNews.map { it.publishedAt}.toSet()
            val newsWithFavorites = articles.map { apiNews ->
                apiNews.copy(isFavorite = favoriteIds.contains(apiNews.publishedAt))
            }
            emit(ApiState.Success(newsWithFavorites))
        } catch (e: HttpException) {
            emit(ApiState.Error(e.message(), e.code()))
        } catch (e: IOException) {
            emit(ApiState.Error("Check internet connection"))
        } catch (e: Exception) {
            emit(ApiState.Error("Unexpected error: ${e.localizedMessage}"))
        }
    }

    override fun getFavoriteArticles(): Flow<List<Article>> {
        return dao.getFavorites().map { it.map { entity -> entity.toDomain() } }
    }

    override suspend fun toggleFavorite(article: Article) {
        if (dao.isFavorite(article.publishedAt)) {
            dao.delete(article.toEntity())
        } else {
            dao.insert(article.toEntity())
        }
    }

    override suspend fun isFavorite(publishedAt: String): Boolean {
        return dao.isFavorite(publishedAt)
    }
}
