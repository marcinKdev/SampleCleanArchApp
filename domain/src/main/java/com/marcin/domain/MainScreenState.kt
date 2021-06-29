package com.marcin.domain

data class MainScreenState(
            val repositories: List<Repository> = emptyList(),
            val loading: Boolean = false
)
