package com.sayantanbanerjee.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var disp : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disp = findViewById(R.id.disp)

        val retrofitService = RetrofitInstance.getRetrofitInstance("https://api.github.com/users/")
            .create(baseService::class.java)

        val responseLiveData : LiveData<Response<githubBase>> = liveData{
            val response = retrofitService.getBaseInfo()
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val name = it.body()?.name
            val location = it.body()?.location
            val public_repos = it.body()?.public_repos
            val followers = it.body()?.followers
            val following = it.body()?.following
            val created_at = it.body()?.created_at
            val updated_at = it.body()?.updated_at

            val display: String = "NAME : " + name + "\n" +
                    "LOCATION : " + location + "\n" +
                    "PUBLIC REPO : " + public_repos + "\n" +
                    "FOLLOWERS : " + followers + "\n" +
                    "FOLLOWING : " + following + "\n" +
                    "CREATED AT : " + created_at + "\n" +
                    "UPDATED AT : " + updated_at + "\n"

            disp.text = display

        })
    }
}
