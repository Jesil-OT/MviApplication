package com.jesil.projectmvi.mviapplication.retrofit

import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun getBlog() : List<BlogNetworkEntity>
}