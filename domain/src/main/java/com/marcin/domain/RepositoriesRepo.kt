package com.marcin.domain


interface RepositoriesRepo {

            suspend fun fetchRepos(page: Int): Result<List<Repository>>
}