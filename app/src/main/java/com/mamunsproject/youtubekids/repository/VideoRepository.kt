package com.mamunsproject.youtubekids.repository

import com.mamunsproject.youtubekids.data.network.LocalDataSource
import com.mamunsproject.youtubekids.data.network.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class VideoRepository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource

) {
    val remote = remoteDataSource
    val local = localDataSource



}