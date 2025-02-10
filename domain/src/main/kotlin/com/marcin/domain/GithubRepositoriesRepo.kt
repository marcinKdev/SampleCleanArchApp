package com.marcin.domain


interface GithubRepositoriesRepo {

            suspend fun fetchRepos(page: Int): Result<List<GithubRepository>>
}