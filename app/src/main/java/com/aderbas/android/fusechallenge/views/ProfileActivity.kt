package com.aderbas.android.fusechallenge.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.aderbas.android.fusechallenge.R
import com.aderbas.android.fusechallenge.adapter.TwitterAdapter
import com.aderbas.android.fusechallenge.databinding.ActivityProfileBinding
import com.aderbas.android.fusechallenge.models.Twitter
import com.aderbas.android.fusechallenge.models.User
import com.aderbas.android.fusechallenge.net.retrofit.service.TwitterService
import com.aderbas.android.fusechallenge.viewmodel.TwitterProfileViewModel
import com.aderbas.android.fusechallenge.viewmodel.TwitterProfileViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.progress
import kotlinx.android.synthetic.main.activity_profile.twitter_list
import kotlinx.android.synthetic.main.activity_search_view.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private val postsViewModel by viewModels<TwitterProfileViewModel>{
        TwitterProfileViewModelFactory(TwitterService.create())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
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
        postsViewModel.twitterResult.observe(this, Observer {
            it?.let {
                twitterAdapter.submitList(it as MutableList<Twitter>)
                twitterAdapter.notifyDataSetChanged()
                progress.visibility = View.GONE
            }
        })
        postsViewModel.errMessage.observe(this, {
            Toast.makeText(this, it as String, Toast.LENGTH_SHORT).show()
            progress.visibility = View.GONE
        })

        handlerIntent(intent)
    }

    /**
     * Handler intent
     * @param intent: Intent
     */
    private fun handlerIntent(intent: Intent){
        intent?.getSerializableExtra("selected").also { selected ->
            val user = selected as User
            progress.visibility = View.VISIBLE
            toolbar_title.title = user.name
            postsViewModel.getPosts(user.screen_name)
            Glide
                .with(this)
                .load(user.profileBackground)
                .centerCrop()
                .into(profile_background_pic)

        }
    }

    private fun adapterOnClick(twitter: Twitter){ }

}