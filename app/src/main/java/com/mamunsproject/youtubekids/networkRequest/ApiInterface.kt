package com.mamunsproject.youtubekids.networkRequest

import com.example.cartoontuberefinefinal.Model.ResponseVideo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("playlistItems?part=snippet%2CcontentDetails")
    suspend fun getAllVideos(
        @Query("maxResults") max: Int,
        @Query("playlistId") playlistId: String?,
        @Query("key") apiKey: String?
    ): Response<ResponseVideo>


    @GET("playlistItems?part=snippet%2CcontentDetails")
    fun getAllVideosFromNonSuspendFun(
        @Query("maxResults") max: Int,
        @Query("playlistId") playlistId: String?,
        @Query("key") apiKey: String?
    ): Call<ResponseVideo>



}