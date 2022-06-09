package com.mamunsproject.youtubekids.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {


    //onConflict means whenever we got new data we replace with old one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: VideoEntity)


    @Query("SELECT * FROM youtube_kids_database ORDER BY id ASC")
    fun readRecipes(): Flow<List<VideoEntity>>

}