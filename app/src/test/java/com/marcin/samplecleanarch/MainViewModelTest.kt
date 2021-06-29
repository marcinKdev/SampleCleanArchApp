package com.marcin.samplecleanarch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.marcin.domain.GetRepositioriesUseCase
import com.marcin.domain.MainScreenState
import com.marcin.domain.Repository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

class MainViewModelTest {

            @JvmField
            @Rule
            val instantExecutorRule = InstantTaskExecutorRule()

            private lateinit var viewModel: MainViewModel

            @Mock
            private lateinit var usecase: GetRepositioriesUseCase

            @Mock
            private lateinit var testObserver: Observer<MainScreenState>

            @Before
            fun setup(){
                        MockitoAnnotations.initMocks(this)
                        RxJavaPlugins.setIoSchedulerHandler{Schedulers.trampoline()}

                        whenever(usecase.execute()).thenReturn(Single.just(listOf()))

                        viewModel = MainViewModel(usecase)
                        viewModel.repositories.observeForever(testObserver)

            }

            @Test
            fun a(){
                        viewModel.fetchRepos()

                        Mockito.verify(testObserver).onChanged(MainScreenState(loading = true))
                        Mockito.verify(testObserver).onChanged(MainScreenState())
            }
}