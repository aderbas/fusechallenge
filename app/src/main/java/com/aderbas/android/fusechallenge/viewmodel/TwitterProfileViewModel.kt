package com.aderbas.android.fusechallenge.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aderbas.android.fusechallenge.models.SearchResult
import com.aderbas.android.fusechallenge.models.Twitter
import com.aderbas.android.fusechallenge.net.retrofit.service.TwitterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException

class TwitterProfileViewModel (private val service: TwitterService) : ViewModel() {
    val twitterResult = MutableLiveData<List<Twitter>>()
    val errMessage = MutableLiveData<String>()

    /**
     * Get selected user posts
     * @param username
     */
    fun getPosts(username: String){
        val request = service.getTwitterTimeline(username)
        request.run {
            enqueue(object: Callback<List<Twitter>>{

                override fun onFailure(call: Call<List<Twitter>>?, t: Throwable) {
                    errMessage.postValue(t.message)
                }
                override fun onResponse(
                    call: Call<List<Twitter>>?,
                    response: Response<List<Twitter>>?
                ) {
                    val list = response?.body() as List<Twitter>
                    if(list.isNotEmpty()) {
                        twitterResult.postValue(list)
                    }else{
                        errMessage.postValue("No results found.")
                    }
                }
            })
        }
    }
}

/**
 * Factory
 */
class TwitterProfileViewModelFactory (private val service: TwitterService) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(TwitterProfileViewModel::class.java)){
            TwitterProfileViewModel(this.service) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}