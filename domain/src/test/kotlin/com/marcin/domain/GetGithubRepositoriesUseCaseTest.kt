package com.marcin.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.test.runTest

class GetGithubRepositoriesUseCaseTest {
      private lateinit var useCase: GetGithubRepositoriesUseCase

      private val githubRepositoriesRepo: GithubRepositoriesRepo = Mockito.mock()

      @BeforeEach
      fun setup() {
            MockitoAnnotations.openMocks(this)
            useCase = GetGithubRepositoriesUseCase(githubRepositoriesRepo)
      }

      @Test
      fun `usecase calls proper repo method`() = runTest {
            useCase.execute()
            verify(githubRepositoriesRepo).fetchRepos(1)
      }
}