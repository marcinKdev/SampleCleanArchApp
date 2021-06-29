package com.marcin.domain

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRepositioriesUseCase @Inject constructor(
      private val repositoriesRepo: RepositoriesRepo,
      private val schedulersProvider: SchedulersProvider
) {
      fun execute(page: Int = 1): Single<List<Repository>> {
            return repositoriesRepo.fetchRepos(page).compose(schedulersProvider.createSingleTransformer())
      }
}