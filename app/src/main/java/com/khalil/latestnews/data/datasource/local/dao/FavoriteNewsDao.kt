package com.khalil.latestnews.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khalil.latestnews.data.datasource.local.entity.FavoriteArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteNewsDao {
    @Query("SELECT * FROM favorite_articles")
    fun getFavorites(): Flow<List<FavoriteArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: FavoriteArticleEntity)

    @Delete
    suspend fun delete(article: FavoriteArticleEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_articles WHERE publishedAt = :publishedAt)")
    suspend fun isFavorite(publishedAt: String): Boolean
}
