/*
package com.marcin.samplecleanarch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.marcin.testdata.RepositoriesTestData
import com.marcin.domain.GetRepositioriesUseCase
import com.marcin.domain.MainScreenState
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {

      @JvmField
      @Rule
      val instantExecutorRule = InstantTaskExecutorRule()

      private lateinit var viewModel: MainViewModel

      @Mock
      private lateinit var usecase: GetRepositioriesUseCase

      @Mock
      private lateinit var testObserver: Observer<MainScreenState>

      private val testRepos = listOf(RepositoriesTestData.repo1, RepositoriesTestData.repo2, RepositoriesTestData.repo3)
      private val testReposSecondPage = testRepos.take(2)

      @Before
      fun setup() {
            MockitoAnnotations.initMocks(this)
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

            whenever(usecase.execute()).thenReturn(Single.just(listOf()))

            viewModel = MainViewModel(usecase)
            viewModel.state.observeForever(testObserver)

      }

      @Test
      fun `Empty list of repositories is served to the VM correct`() {
            viewModel.fetchRepos()

            Mockito.verify(testObserver).onChanged(MainScreenState(loading = true))
            Mockito.verify(testObserver).onChanged(MainScreenState())
      }

      @Test
      fun `Single page of repositories is served to the VM correct`() {
            whenever(usecase.execute()).thenReturn(Single.just(testRepos))

            viewModel.fetchRepos()

            Mockito.verify(testObserver).onChanged(MainScreenState(loading = true))
            Mockito.verify(testObserver).onChanged(MainScreenState(repositories = testRepos))
      }

      @Test
      fun `The first and the second page of repositories is served to the VM correct`() {
            // fetching the 1st page

            whenever(usecase.execute()).thenReturn(Single.just(testRepos))
            whenever(usecase.execute(2)).thenReturn(Single.just(testReposSecondPage))

            viewModel.fetchRepos()

            Mockito.verify(testObserver).onChanged(MainScreenState(loading = true))
            Mockito.verify(testObserver).onChanged(MainScreenState(repositories = testRepos))

            // fetching the 2nd page

            reset(testObserver)

            viewModel.fetchReposFurtherPage()

            assertThat(viewModel.page, equalTo(2))

            Mockito.verify(testObserver).onChanged(MainScreenState(repositories = testRepos, loading = true))
            Mockito.verify(testObserver).onChanged(MainScreenState(repositories = testRepos + testReposSecondPage))
      }

      @Test
      fun `An error state is served to the VM correct`() {
            val error = Throwable()

            whenever(usecase.execute()).thenReturn(Single.error(error))

            viewModel.fetchRepos()

            Mockito.verify(testObserver).onChanged(MainScreenState(loading = true))
            Mockito.verify(testObserver).onChanged(MainScreenState(error = error))

            // all is back to normal

            reset(testObserver)

            whenever(usecase.execute()).thenReturn(Single.just(testRepos))

            viewModel.fetchRepos()

            Mockito.verify(testObserver).onChanged(MainScreenState(loading = true))
            Mockito.verify(testObserver).onChanged(MainScreenState(repositories = testRepos))
      }
}*/
