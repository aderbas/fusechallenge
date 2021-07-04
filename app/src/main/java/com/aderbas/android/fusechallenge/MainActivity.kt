package com.aderbas.android.fusechallenge

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.aderbas.android.fusechallenge.models.SearchResult
import com.aderbas.android.fusechallenge.models.Twitter
import com.aderbas.android.fusechallenge.net.retrofit.conf.RetrofitConfig
import com.aderbas.android.fusechallenge.net.retrofit.service.TwitterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu);

        val sManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val sInput = menu?.findItem(R.id.search)?.actionView as SearchView
        sInput.onActionViewExpanded()
        sInput.requestFocusFromTouch()
        sInput.requestFocus()
        sInput.apply {
            setSearchableInfo(sManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false
        }
        return true;
    }
}