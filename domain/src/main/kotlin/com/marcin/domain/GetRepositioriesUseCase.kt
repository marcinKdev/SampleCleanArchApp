package com.marcin.domain

import javax.inject.Inject

class GetRepositioriesUseCase @Inject constructor(
      private val repositoriesRepo: RepositoriesRepo,
) {
      suspend fun execute(page: Int = 1): Result<List<Repository>> {
            return repositoriesRepo.fetchRepos(page)
      }
}