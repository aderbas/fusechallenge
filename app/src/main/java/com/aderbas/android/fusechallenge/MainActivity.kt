package com.aderbas.android.fusechallenge

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        handlerIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        this.handlerIntent(intent);
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
        }
        return true;
    }

    private fun handlerIntent(intent: Intent?) {
        if(intent?.action == Intent.ACTION_SEARCH){
            val query = intent.getStringExtra(SearchManager.QUERY);
            Log.i("MainActivity", "Search for: " + query);
        }
    }
}