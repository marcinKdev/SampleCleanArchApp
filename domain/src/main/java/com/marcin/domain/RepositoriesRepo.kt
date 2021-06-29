package com.marcin.domain

import io.reactivex.rxjava3.core.Single

interface RepositoriesRepo {

            fun fetchRepos(page: Int): Single<List<Repository>>
}