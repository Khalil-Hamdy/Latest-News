package com.khalil.latestnews.doman.usecases

import com.khalil.latestnews.doman.model.Article
import com.khalil.latestnews.doman.repo.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getFavoriteArticles()
    }
}