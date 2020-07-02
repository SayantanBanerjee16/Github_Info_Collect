package com.sayantanbanerjee.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.sayantanbanerjee.github.contribution.contribution
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var disp : TextView
    private lateinit var displayContribution : TextView
    private lateinit var contribution: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disp = findViewById(R.id.disp)
        displayContribution = findViewById(R.id.displayContribution)

        //BASE INFO

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

        //----------------------------------------------
        // CONTRIBUTION INFO

        val contributionService = RetrofitInstance.getRetrofitInstance("https://github-contributions-api.now.sh/v1/")
            .create(contributionService::class.java)

        val contributionLiveData : LiveData<Response<contribution>> = liveData{
            val response = contributionService.getContributionInfo()
            emit(response)
        }

        contributionLiveData.observe(this, Observer {
            contribution = "CONTRIBUTIONS : \n\n"
            val contributionList = it.body()
            for(i in 0 .. (contributionList!!.years.size - 1)){
                val yearItem = contributionList.years[i]
                contribution += yearItem.year + " : "+ yearItem.total + "\n"
            }
            displayContribution.text = contribution
        })
    }
}
