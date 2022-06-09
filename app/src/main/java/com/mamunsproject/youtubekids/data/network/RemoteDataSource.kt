package com.mamunsproject.youtubekids.data.network

import com.mamunsproject.youtubekids.networkRequest.ApiInterface
import com.mamunsproject.youtubekids.roomDB.VideoDao
import com.mamunsproject.youtubekids.roomDB.VideoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiInterface: ApiInterface
){
    suspend fun getPost(maxResult: Int, playListID: String, apiKey: String) =
        apiInterface.getAllVideos(maxResult, playListID, apiKey)
}