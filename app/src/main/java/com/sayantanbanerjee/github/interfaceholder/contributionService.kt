package com.sayantanbanerjee.github.interfaceholder

import com.sayantanbanerjee.github.contribution.contribution
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface contributionService {

    @GET("{id}")
    suspend fun getContributionInfo(@Path(value = "id") id:String) : Response<contribution>
}
