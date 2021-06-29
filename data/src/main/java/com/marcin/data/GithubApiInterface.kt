package com.marcin.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.github.com/"

interface GithubApiInterface {

            @GET("/search/repositories?q=kotlin")
            fun getRepos(@Query("page") page: Int): Single<RepositoriesDto>

}