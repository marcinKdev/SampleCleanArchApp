package com.marcin.samplecleanarch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcin.domain.GetGithubRepositoriesUseCase
import com.marcin.domain.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getGithubRepositoriesUseCase: GetGithubRepositoriesUseCase) : ViewModel() {

      internal var page = 1

      private val _state = MutableStateFlow(MainScreenState())
      val state: StateFlow<MainScreenState>
            get() = _state

      fun fetchRepos() {
            val currentRepos = _state.value.repositories

            _state.value = MainScreenState(repositories = currentRepos, loading = true)

            viewModelScope.launch {
                  val result = getGithubRepositoriesUseCase.execute()

                  when (result.isSuccess) {
                        true -> _state.value = MainScreenState(repositories = result.getOrElse { listOf() })
                        else -> _state.value = MainScreenState(error = result.exceptionOrNull())
                  }

            }


      }

      fun fetchReposFurtherPage() {
            page++

            val currentRepos = _state.value.repositories

            _state.value = MainScreenState(repositories = currentRepos, loadingMore = true)

            viewModelScope.launch {
                  val result = getGithubRepositoriesUseCase.execute(page)

                  when (result.isSuccess) {
                        true -> _state.value = MainScreenState(repositories = currentRepos + result.getOrElse { listOf() })
                        else -> _state.value = MainScreenState(error = result.exceptionOrNull())
                  }

            }
      }

}