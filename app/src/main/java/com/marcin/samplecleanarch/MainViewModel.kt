package com.marcin.samplecleanarch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcin.domain.GetRepositioriesUseCase
import com.marcin.domain.MainScreenState
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getRepositioriesUseCase: GetRepositioriesUseCase) : ViewModel() {

            internal var page = 1

            private val _repositories = MutableLiveData<MainScreenState>()
            val repositories: LiveData<MainScreenState>
                        get() = _repositories

            fun fetchRepos() {
                        val currentRepos = _repositories.value?.repositories ?: emptyList()

                        _repositories.value = MainScreenState(repositories = currentRepos, loading = true)

                        getRepositioriesUseCase.execute()
                                    .subscribe(
                                                { _repositories.value = MainScreenState(repositories = it) },
                                                { println("Error fetching repositories") }
                                    )
            }

            fun fetchReposFurtherPage() {
                        page++

                        val currentRepos = _repositories.value?.repositories ?: emptyList()

                        _repositories.value = MainScreenState(repositories = currentRepos, loading = true)

                        getRepositioriesUseCase.execute(page)
                                    .subscribe(
                                                { _repositories.value = MainScreenState(repositories = currentRepos + it) },
                                                { println("Error fetching repositories") }
                                    )
            }

}