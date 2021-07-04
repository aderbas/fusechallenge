package com.aderbas.android.fusechallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aderbas.android.fusechallenge.R
import com.aderbas.android.fusechallenge.models.Twitter

class TwitterAdapter(private val onCLick: (Twitter) -> Unit):
    ListAdapter<Twitter, TwitterAdapter.ViewHolder>(TwitterDiffCallback) {

    class ViewHolder(view : View, val onCLick: (Twitter) -> Unit)
        : RecyclerView.ViewHolder(view) {
        private val dateTextView: TextView = view.findViewById(R.id.create_at)
        private val userTextView: TextView = view.findViewById(R.id.user_name)
        private val twitterTextView: TextView = view.findViewById(R.id.twitter_content)
        private var current: Twitter? = null

        init {
            view.setOnClickListener{
                current?.let {
                    onCLick(it)
                }
            }
        }

        fun bind(twitter: Twitter){
            current = twitter
            dateTextView.text = twitter.createdAt
            userTextView.text = twitter.user.name
            twitterTextView.text = twitter.bodyText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.twitter_search_row, parent, false)
        return ViewHolder(view, onCLick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val twitter = getItem(position)
        holder.bind(twitter)
    }

    //override fun getItemCount() = 1
}

object TwitterDiffCallback : DiffUtil.ItemCallback<Twitter>() {
    override fun areItemsTheSame(oldItem: Twitter, newItem: Twitter): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Twitter, newItem: Twitter): Boolean {
        return oldItem.id == newItem.id
    }
}