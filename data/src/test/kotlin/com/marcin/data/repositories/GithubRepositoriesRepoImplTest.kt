package com.marcin.data.repositories

import com.marcin.data.GithubApiInterface
import com.marcin.domain.GithubRepositories
import com.marcin.domain.GithubRepositoriesRepo
import com.marcin.domain.GithubRepository
import com.marcin.testdata.RepositoriesTestData
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

class GithubRepositoriesRepoImplTest {

      private lateinit var repositoryUnderTest: GithubRepositoriesRepo
      private val apiInterfaceMock: GithubApiInterface = mock()

      private val emptyResponse = listOf<GithubRepository>()
      private val sampleResponse = listOf(RepositoriesTestData.repo1, RepositoriesTestData.repo2)

      @BeforeEach
      fun setup() {
            MockitoAnnotations.openMocks(this)
            repositoryUnderTest = GithubRepositoriesRepoImpl(apiInterfaceMock)
      }

      @Test
      fun `Repository provides empty response`() =
            runTest {
                  whenever(apiInterfaceMock.getRepos(anyInt())).thenReturn(Response.success(GithubRepositories(listOf())))

                  val result = repositoryUnderTest.fetchRepos(1).getOrNull()
                  assertEquals(result, emptyResponse)
            }

      @Test
      fun `Repository provides sample response`() =
            runTest {
                  whenever(apiInterfaceMock.getRepos(anyInt())).thenReturn(Response.success(GithubRepositories(sampleResponse)))

                  val result = repositoryUnderTest.fetchRepos(1).getOrNull()
                  assertEquals(result, sampleResponse)
            }

      @Test
      fun `Repository provides error`() =
            runTest {
                  whenever(apiInterfaceMock.getRepos(anyInt())).thenReturn(Response.error(404, "error".toResponseBody()))

                  val result = repositoryUnderTest.fetchRepos(1)
                  assertTrue(result.isFailure)
            }

}
