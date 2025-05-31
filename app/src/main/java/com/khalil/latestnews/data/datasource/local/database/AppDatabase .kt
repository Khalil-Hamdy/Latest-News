package com.khalil.latestnews.data.datasource.local.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.khalil.latestnews.data.datasource.local.dao.FavoriteNewsDao
import com.khalil.latestnews.data.datasource.local.entity.FavoriteArticleEntity


@Database(entities = [FavoriteArticleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteNewsDao
}