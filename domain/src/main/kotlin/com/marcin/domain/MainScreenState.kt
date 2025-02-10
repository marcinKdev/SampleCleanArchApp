package com.marcin.domain

data class MainScreenState(
      val repositories: List<GithubRepository> = listOf(),
      val loading: Boolean = false,
      val loadingMore: Boolean = false,
      val error: Throwable? = null
)
