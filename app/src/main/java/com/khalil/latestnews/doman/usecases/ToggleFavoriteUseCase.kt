package com.khalil.latestnews.doman.usecases

import com.khalil.latestnews.doman.model.Article
import com.khalil.latestnews.doman.repo.NewsRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        repository.toggleFavorite(article)
    }
}