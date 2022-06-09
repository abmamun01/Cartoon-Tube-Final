package com.mamunsproject.mvvm_dagger_hilt

import android.content.Context
import androidx.room.Room
import com.mamunsproject.youtubekids.roomDB.VideoDatabase
import com.mamunsproject.youtubekids.utils.Constants_.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        VideoDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: VideoDatabase) = database.videoDao()

}