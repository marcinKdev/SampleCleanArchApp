package com.marcin.data

import com.marcin.domain.Repositories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.github.com/"

interface GithubApiInterface {

      @GET("/search/repositories?q=kotlin")
      suspend fun getRepos(@Query("page") page: Int): Response<Repositories>

}