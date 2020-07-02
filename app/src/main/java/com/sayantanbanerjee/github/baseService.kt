package com.sayantanbanerjee.github

import com.sayantanbanerjee.github.githubBase
import retrofit2.Response
import retrofit2.http.GET

interface baseService {

    @GET("sayantanbanerjee16")
    suspend fun getBaseInfo() : Response<githubBase>
}