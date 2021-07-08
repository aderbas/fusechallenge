package com.aderbas.android.fusechallenge.views

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.aderbas.android.fusechallenge.R
import com.aderbas.android.fusechallenge.adapter.TwitterAdapter
import com.aderbas.android.fusechallenge.models.Twitter
import com.aderbas.android.fusechallenge.net.retrofit.service.TwitterService
import com.aderbas.android.fusechallenge.viewmodel.TwitterModelFactory
import com.aderbas.android.fusechallenge.viewmodel.TwitterViewModel
import com.aderbas.android.fusechallenge.databinding.ActivitySearchViewBinding
import kotlinx.android.synthetic.main.activity_search_view.*

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchViewBinding

    private val twitterListViewModel by viewModels<TwitterViewModel> {
        TwitterModelFactory(TwitterService.create())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)
        binding = ActivitySearchViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handlerIntent(intent)
    }

    /**
     * Setup
     */
    private fun init(){
        var twitterAdapter = TwitterAdapter { twitter -> adapterOnClick(twitter)  }
        val recycleView : RecyclerView = twitter_list
        recycleView.adapter = twitterAdapter
        recycleView.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        twitterListViewModel.twitterResult.observe(this, {
            it?.let {
                twitterAdapter.submitList(it as MutableList<Twitter>)
                twitterAdapter.notifyDataSetChanged()
                progress.visibility = View.GONE
            }
        })
        twitterListViewModel.errMessage.observe(this, {
            Toast.makeText(this, it as String, Toast.LENGTH_SHORT).show()
            progress.visibility = View.GONE
        })

        handlerIntent(intent)
    }

    /**
     * handler intent
     * @param intent
     */
    private fun handlerIntent(intent: Intent?){
        if(intent?.action == Intent.ACTION_SEARCH){
            progress.visibility = View.VISIBLE
            intent?.getStringExtra(SearchManager.QUERY)?.also { query ->
                twitterListViewModel.search(query)
            }
        }
    }

    /**
     * User click a item from listview
     * @param twitter
     */
    private fun adapterOnClick(twitter: Twitter){
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("selected", twitter.user)
        startActivity(intent)
    }
}