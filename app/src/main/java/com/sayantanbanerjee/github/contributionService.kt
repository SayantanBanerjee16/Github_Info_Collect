package com.sayantanbanerjee.github

import com.sayantanbanerjee.github.contribution.contribution
import retrofit2.Response
import retrofit2.http.GET

interface contributionService {

    @GET("sayantanbanerjee16")
    suspend fun getContributionInfo() : Response<contribution>
}
