package com.marcin.samplecleanarch

import com.marcin.domain.GetGithubRepositoriesUseCase
import com.marcin.domain.MainScreenState
import com.marcin.samplecleanarch.utils.InstantExecutorExtension
import com.marcin.samplecleanarch.utils.MainCoroutineRule
import com.marcin.testdata.RepositoriesTestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantExecutorExtension::class, MainCoroutineRule::class)
class MainViewModelTest {

      private lateinit var viewModel: MainViewModel

      @Mock
      private lateinit var usecase: GetGithubRepositoriesUseCase

      @BeforeEach
      fun setup() {
            MockitoAnnotations.openMocks(this)

            viewModel = MainViewModel(usecase)

      }

      @Test
      fun `Loading state gets reflected correct`() = runTest {
            whenever(usecase.execute(any())).thenReturn(Result.success(listOf()))

            viewModel.fetchRepos()

            assertEquals(MainScreenState(), viewModel.state.value)
      }

      @Test
      fun `Populated state gets reflected correct`() = runTest {
            whenever(usecase.execute(any())).thenReturn(Result.success(listOf(RepositoriesTestData.repo1)))

            viewModel.fetchRepos()

            assertEquals(MainScreenState(repositories = listOf(RepositoriesTestData.repo1)), viewModel.state.value)
      }

      @Test
      fun `Error state gets reflected correct`() = runTest {
            whenever(usecase.execute(any())).thenReturn(Result.failure(Exception()))

            viewModel.fetchRepos()

            assertTrue(viewModel.state.value.error is Exception)
      }
}
