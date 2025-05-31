package com.khalil.latestnews.doman.usecases

import com.khalil.latestnews.doman.model.Article
import com.khalil.latestnews.doman.repo.NewsRepository
import com.khalil.paymobtask.utils.ApiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<ApiState<List<Article>>> {
        return repository.fetchNews()
    }
}