package com.khalil.latestnews.doman.usecases

import com.khalil.latestnews.doman.repo.NewsRepository
import javax.inject.Inject

class IsArticleFavoriteUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(publishedAt: String): Boolean {
        return repository.isFavorite(publishedAt)
    }
}