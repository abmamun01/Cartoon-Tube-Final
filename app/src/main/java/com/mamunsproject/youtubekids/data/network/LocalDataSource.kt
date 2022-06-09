package com.mamunsproject.youtubekids.data.network

import com.mamunsproject.youtubekids.roomDB.VideoDao
import com.mamunsproject.youtubekids.roomDB.VideoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: VideoDao
) {

    fun readDatabase(): Flow<List<VideoEntity>> {
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: VideoEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }
}