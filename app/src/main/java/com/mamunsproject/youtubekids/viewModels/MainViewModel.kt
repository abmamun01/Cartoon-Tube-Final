package com.mamunsproject.youtubekids.viewModels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.cartoontuberefinefinal.Model.ResponseVideo
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mamunsproject.youtubekids.Resource_
import com.mamunsproject.youtubekids.repository.VideoRepository
import com.mamunsproject.youtubekids.roomDB.VideoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel @ViewModelInject constructor(
    application: Application,
    private val videoRepository: VideoRepository
) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext


    companion object {
        lateinit var API_KEY_MAIN: String
    }

    var firebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var documentReference: DocumentReference
    lateinit var documentReference2: DocumentReference


    /** ROOM DATABASE*/

    val readVideoRequest: LiveData<List<VideoEntity>> =
        videoRepository.local.readDatabase().asLiveData()

    private fun insertCartoonForOffline(videoEntity: VideoEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            videoRepository.local.insertRecipes(videoEntity)
        }

    /** RETROFIT */
    val videoViewModel: MutableLiveData<Resource_<ResponseVideo>> = MutableLiveData()


    val maxResult: Int = 80
    val playListID: String = "PLw3_aVliusmVGs2033IMAYhLNgm7NxfET"
    //Default
    val defaultApiKey: String = "AIzaSyDfhOwSeD1uj6cZyIkSoyGLJoWDj1iAhBc"


    fun getHomeKeyFromFirestore(apiKeyFromFirestore: String, playListID: String) =
        viewModelScope.launch {

            Log.d(
                "ERORRRRRR",
                "getHomeKeyFromFirestore Called 000000: ${Thread.currentThread().name} "
            )

            getVideosFromViewModel(apiKeyFromFirestore, playListID)

        }


    /** --------- Caching Data ------------ */

    private fun offlineCacheRecipes(foodRecipe: ResponseVideo) {

        val recipesEntity = VideoEntity(foodRecipe)
        insertCartoonForOffline(recipesEntity)

    }
    /** --------- Caching Data ------------ */



    /** --------- Caching Data ------------ */





    private suspend fun getVideosFromViewModel(apiKeyFromFirestore: String, playListIDF: String) {

        Log.d("ERORRRRRR", "offlineCacheRecipes Called : ${Thread.currentThread().name} ")

        if (context.isInternetAvailable()) {

            try {

                Log.d(
                    "ERORRRRRR",
                    "offlineCacheRecipes In Try Catch : ${Thread.currentThread().name} "
                )

                videoViewModel.postValue(Resource_.Loading())
                val response =
                    videoRepository.remote.getPost(maxResult, playListIDF, apiKeyFromFirestore)
                //Fragment will automatically notify about that change
                // videoViewModel.postValue(handleVideoResponse(response))
                videoViewModel.value = handleVideoResponse(response)

                val videosVideo = videoViewModel.value!!.data
                if (videosVideo != null) {
                    Log.d(
                        "ERORRRRRR",
                        "offlineCacheRecipes Called: ${Thread.currentThread().name} "
                    )

                    offlineCacheRecipes(videosVideo)
                }

            } catch (e: Exception) {

                videoViewModel.value = Resource_.Error("Recipes Note Found")
                Log.d("ERORRRRRR", "Caching Error: ${Thread.currentThread().name} ")

            }

        } else {
            Log.d("SFDDVSDFDF", "No InterNet: ${Thread.currentThread().name} ")
        }

        Log.d("THREADDDSD", "getVideosFromViewModel: ${Thread.currentThread().name} ")


    }


    private fun handleVideoResponse(response: Response<ResponseVideo>): Resource_<ResponseVideo> {

        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource_.Success(resultResponse)
            }
        }
        return Resource_.Error(response.message())
    }


    @SuppressLint("ObsoleteSdkInt")
    fun Context.isInternetAvailable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
                return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val networks = cm.allNetworks
                for (n in networks) {
                    val nInfo = cm.getNetworkInfo(n)
                    if (nInfo != null && nInfo.isConnected) return true
                }
            }
            else -> {
                val networks = cm.allNetworkInfo
                for (nInfo in networks) {
                    if (nInfo != null && nInfo.isConnected) return true
                }
            }
        }
        return false
    }


    fun getApiKEY() {

        documentReference2 = firebaseFirestore.collection("API_KEY").document("Main_Api")
        documentReference2.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {

                API_KEY_MAIN = documentSnapshot.getString("api_field").toString()

                documentReference = firebaseFirestore.collection( "AllPlayListKEY").document("ALL_CARTOON_ID")
                documentReference.get().addOnSuccessListener { documentSnapshot2 ->
                    if (documentSnapshot2.exists()) {

                        val playlistKey = documentSnapshot2.getString("ALL_CARTOON_ID").toString()

                        getHomeKeyFromFirestore(API_KEY_MAIN, playlistKey)
                    }
                }

            }
        }

    }

/*
    fun getTomJerryKey() {

        viewModelScope.launch(Dispatchers.IO) {

            documentReference = firebaseFirestore.collection("AllPlayListKEY").document("TOM_JERRY_ID")
            documentReference.get().addOnSuccessListener { documentSnapshot2 ->
                if (documentSnapshot2.exists()) {

                    val playlistKey = documentSnapshot2.getString("TOM_JERRY_ID").toString()

                    getHomeKeyFromFirestore(API_KEY_MAIN, playlistKey)
                }
            }

        }
    }


    fun getBen10Key() {

        viewModelScope.launch(Dispatchers.IO) {

            documentReference = firebaseFirestore.collection("AllPlayListKEY").document("BEN_10_ID")
            documentReference.get().addOnSuccessListener { documentSnapshot2 ->
                if (documentSnapshot2.exists()) {

                    val playlistKey = documentSnapshot2.getString("BEN_10_ID").toString()

                    getHomeKeyFromFirestore(API_KEY_MAIN, playlistKey)
                }
            }

        }

    }


    fun getMrbeanKey() {

        viewModelScope.launch(Dispatchers.IO) {

            documentReference = firebaseFirestore.collection("AllPlayListKEY").document("MR_BEAN_ID")
            documentReference.get().addOnSuccessListener { documentSnapshot2 ->
                if (documentSnapshot2.exists()) {

                    val playlistKey = documentSnapshot2.getString("MR_BEAN_ID").toString()

                    getHomeKeyFromFirestore(API_KEY_MAIN, playlistKey)
                }
            }

        }

    }


    fun getBanglaCartoonKey() {

        viewModelScope.launch(Dispatchers.IO) {

            documentReference =
                firebaseFirestore.collection("AllPlayListKEY").document("BanglaCartoonPlayListKEY")
            documentReference.get().addOnSuccessListener { documentSnapshot2 ->
                if (documentSnapshot2.exists()) {

                    val playlistKey =
                        documentSnapshot2.getString("BanglaCartoonPlayListKEY").toString()

                    getHomeKeyFromFirestore(API_KEY_MAIN, playlistKey)
                }
            }

        }

    }


    fun getHindiCartoonKey() {

        viewModelScope.launch(Dispatchers.IO) {

            documentReference =
                firebaseFirestore.collection("AllPlayListKEY").document("HINDI_CARTOON_ID")
            documentReference.get().addOnSuccessListener { documentSnapshot2 ->
                if (documentSnapshot2.exists()) {

                    val playlistKey = documentSnapshot2.getString("HINDI_CARTOON_ID").toString()

                    getHomeKeyFromFirestore(API_KEY_MAIN, playlistKey)
                }
            }

        }

    }


    fun getDoreamonCartoonKey() {

        viewModelScope.launch(Dispatchers.IO) {

            documentReference = firebaseFirestore.collection("AllPlayListKEY").document("DOREAMON_ID")
            documentReference.get().addOnSuccessListener { documentSnapshot2 ->
                if (documentSnapshot2.exists()) {

                    val playlistKey = documentSnapshot2.getString("DOREAMON_ID").toString()

                    getHomeKeyFromFirestore(API_KEY_MAIN, playlistKey)
                }
            }

        }

    }


    fun getOggyCartoonKey() {

        viewModelScope.launch(Dispatchers.IO) {

            documentReference =
                firebaseFirestore.collection("AllPlayListKEY").document("OGGY_AND_COCKROACHES")
            documentReference.get().addOnSuccessListener { documentSnapshot2 ->
                if (documentSnapshot2.exists()) {

                    val playlistKey = documentSnapshot2.getString("OGGY_AND_COCKROACHES").toString()

                    getHomeKeyFromFirestore(API_KEY_MAIN, playlistKey)
                }
            }

        }

    }
*/


}