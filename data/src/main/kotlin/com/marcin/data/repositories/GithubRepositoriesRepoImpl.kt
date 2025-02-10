package com.marcin.data.repositories

import com.marcin.data.GithubApiInterface
import com.marcin.domain.GithubRepositoriesRepo
import com.marcin.domain.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubRepositoriesRepoImpl @Inject constructor(
      private val githubApiInterface: GithubApiInterface
) : GithubRepositoriesRepo {
      override suspend fun fetchRepos(page: Int): Result<List<GithubRepository>> {
            try {
                  val result = githubApiInterface.getRepos(page)

                  return withContext(Dispatchers.IO) {
                        if (result.isSuccessful) Result.success(result.body()?.items ?: listOf())
                        else Result.failure(Exception())
                  }
            } catch (e: Exception) {
                  return Result.failure(e)
            }
      }

}