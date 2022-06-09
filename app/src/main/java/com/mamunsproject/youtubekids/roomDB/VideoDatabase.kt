package com.mamunsproject.youtubekids.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter_::class)
abstract class VideoDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao

}