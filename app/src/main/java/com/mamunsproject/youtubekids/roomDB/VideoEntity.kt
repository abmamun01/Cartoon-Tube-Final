package com.mamunsproject.youtubekids.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cartoontuberefinefinal.Model.ResponseVideo
import com.mamunsproject.youtubekids.utils.Constants_.Companion.DATABASE_NAME


@Entity(tableName = DATABASE_NAME)
class VideoEntity (
    var video_: ResponseVideo
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}