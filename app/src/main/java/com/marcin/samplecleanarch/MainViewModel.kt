package com.marcin.samplecleanarch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcin.domain.GetRepositioriesUseCase
import com.marcin.domain.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getRepositioriesUseCase: GetRepositioriesUseCase) : ViewModel() {

      internal var page = 1

      private val _state = MutableLiveData<MainScreenState>()
      val state: LiveData<MainScreenState>
            get() = _state

      fun fetchRepos() {
            val currentRepos = _state.value?.repositories ?: emptyList()

            _state.value = MainScreenState(repositories = currentRepos, loading = true)

            viewModelScope.launch {
                  val result = getRepositioriesUseCase.execute()

                  when (result.isSuccess) {
                        true -> _state.value = MainScreenState(repositories = result.getOrNull()!!)
                        else -> _state.value = MainScreenState(error = result.exceptionOrNull())
                  }

            }


      }

      fun fetchReposFurtherPage() {
            page++

            val currentRepos = _state.value?.repositories ?: emptyList()

            _state.value = MainScreenState(repositories = currentRepos, loading = true)

            viewModelScope.launch {
                  val result = getRepositioriesUseCase.execute(page)

                  when (result.isSuccess) {
                        true -> _state.value = MainScreenState(repositories = currentRepos + result.getOrNull()!!)
                        else -> _state.value = MainScreenState(error = result.exceptionOrNull())
                  }

            }
      }

}