package com.marcin.data.repositories

import com.marcin.data.GithubApiInterface
import com.marcin.domain.RepositoriesRepo
import com.marcin.domain.Repository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoriesRepoImpl @Inject constructor(
      private val githubApiInterface: GithubApiInterface
) : RepositoriesRepo {
      override fun fetchRepos(page: Int): Single<List<Repository>> {
            return githubApiInterface.getRepos(page).flatMap {
                  if (it.items.isEmpty()) {
                        Single.error(Throwable())
                  } else {
                        Single.just(it.items.mapNotNull { repositoryDto -> repositoryDto.map() })
                  }
            }
      }
}