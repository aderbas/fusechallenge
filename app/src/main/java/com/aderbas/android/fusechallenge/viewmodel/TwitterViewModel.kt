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

class TwitterViewModel constructor(private val service: TwitterService) : ViewModel() {
    val twitterResult = MutableLiveData<List<Twitter>>()
    val errMessage = MutableLiveData<String>()

    fun search(query: String) {
        val request = service.search(query)
        request.run {
            enqueue(object: Callback<SearchResult> {
                override fun onFailure(call: Call<SearchResult>?, t: Throwable) {
                    Log.e("SearchError", t.message as String)
                    errMessage.postValue(t.message)
                }

                override fun onResponse(
                    call: Call<SearchResult>?,
                    response: Response<SearchResult>?
                ) {
                    val list = response?.body()?.statuses as List<Twitter>
                    Log.i("### RESPONSE ###", "Twitters found: ${list.size}")
                    twitterResult.postValue(list)
                }
            })
        }
    }
}

class TwitterModelFactory constructor(private val service: TwitterService): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TwitterViewModel::class.java)){
            TwitterViewModel(this.service) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}