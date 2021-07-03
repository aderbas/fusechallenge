package com.aderbas.android.fusechallenge.views

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aderbas.android.fusechallenge.R

class SearchView : AppCompatActivity() {

    private val TAG = "SearchView";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)
        Log.i(this.TAG, "Search View Opened")
        handlerIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handlerIntent(intent)
    }

    private fun handlerIntent(intent: Intent?){
        Log.i(TAG, "Recebeu o Intent?")
        if(intent?.action == Intent.ACTION_SEARCH){
            intent?.getStringExtra(SearchManager.QUERY)?.also {query ->
                Log.i(TAG, "Search by $query")
            }
        }
    }
}