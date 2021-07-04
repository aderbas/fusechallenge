package com.aderbas.android.fusechallenge.views

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.aderbas.android.fusechallenge.R
import com.aderbas.android.fusechallenge.adapter.TwitterAdapter
import com.aderbas.android.fusechallenge.models.Twitter
import com.aderbas.android.fusechallenge.net.retrofit.service.TwitterService
import com.aderbas.android.fusechallenge.viewmodel.TwitterModelFactory
import com.aderbas.android.fusechallenge.viewmodel.TwitterViewModel
import com.aderbas.android.fusechallenge.databinding.ActivitySearchViewBinding

class SearchView : AppCompatActivity() {

    private val TAG = "SearchView";
    private lateinit var binding: ActivitySearchViewBinding

    private val twitterListViewModel by viewModels<TwitterViewModel> {
        TwitterModelFactory(TwitterService.create())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)
        binding = ActivitySearchViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var twitterAdapter = TwitterAdapter { twitter -> adapterOnClick(twitter)  }
        val recycleView : RecyclerView = findViewById(R.id.twitter_list)
        recycleView.adapter = twitterAdapter
        twitterListViewModel.twitterResult.observe(this, Observer {
                it?.let {
                    twitterAdapter.submitList(it as MutableList<Twitter>)
                    twitterAdapter.notifyDataSetChanged()
                }
            }
        )
        handlerIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handlerIntent(intent)
    }

    private fun handlerIntent(intent: Intent?){
        if(intent?.action == Intent.ACTION_SEARCH){
            intent?.getStringExtra(SearchManager.QUERY)?.also { query ->
                //val call = TwitterService.create().search(query)
                twitterListViewModel.search(query)
            }
        }
    }

    private fun adapterOnClick(twitter: Twitter){

    }
}