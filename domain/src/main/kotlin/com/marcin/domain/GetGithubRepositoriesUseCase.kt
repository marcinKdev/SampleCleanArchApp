package com.marcin.domain

import javax.inject.Inject

class GetGithubRepositoriesUseCase @Inject constructor(
      private val githubRepositoriesRepo: GithubRepositoriesRepo,
) {
      suspend fun execute(page: Int = 1): Result<List<GithubRepository>> {
            return githubRepositoriesRepo.fetchRepos(page)
      }
}