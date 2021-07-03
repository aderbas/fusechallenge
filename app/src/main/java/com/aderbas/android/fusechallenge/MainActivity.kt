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
        //handlerIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //handlerIntent(intent);
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

    private fun handlerIntent(intent: Intent?) {
        if(intent?.action == Intent.ACTION_SEARCH){
            val query = intent.getStringExtra(SearchManager.QUERY) as String;
            Log.i("MainActivity", "Search for: " + query);
            val endpoint = RetrofitConfig.buildService(TwitterService::class.java)
            val call = endpoint.search(query)

            call.enqueue(object: Callback<SearchResult>{
                override fun onFailure(call: Call<SearchResult>?, t: Throwable?) {
                    Toast.makeText(baseContext, t?.message, Toast.LENGTH_SHORT).show()
                    Log.e("MainActivity", t?.message as String)
                }

                override fun onResponse(
                    call: Call<SearchResult>?,
                    response: Response<SearchResult>?
                ) {
                    //Log.i("MainActivity", response?.body().toString())
                    val list = response?.body()?.statuses as List<Twitter>
                    list.forEach {
                        Log.i("MainActivity", it.bodyText)
                    }
                }
            })
        }
    }
}