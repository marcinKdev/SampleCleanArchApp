package com.marcin.data.repositories

import com.marcin.data.GithubApiInterface
import com.marcin.domain.RepositoriesRepo
import com.marcin.domain.Repository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoriesRepoImpl @Inject constructor(
      private val githubApiInterface: GithubApiInterface
) : RepositoriesRepo {
      override suspend fun fetchRepos(page: Int): Result<List<Repository>> {
            val result = githubApiInterface.getRepos(page)

            return withContext(Dispatchers.IO) {
                  if (result.isSuccessful) Result.success(result.body()?.items ?: listOf())
                  else Result.failure(Exception())
            }
      }
}