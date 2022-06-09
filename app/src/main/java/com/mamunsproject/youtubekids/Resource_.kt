package com.mamunsproject.youtubekids

sealed class Resource_<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : Resource_<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource_<T>(data, message)
    class Loading<T> : Resource_<T>()


}