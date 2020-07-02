package com.sayantanbanerjee.github.interfaceholder

import com.sayantanbanerjee.github.basegithub.githubBase
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface baseService {

    @GET("{id}")
    suspend fun getBaseInfo(@Path(value = "id") id: String) : Response<githubBase>
}