package com.marcin.samplecleanarch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcin.domain.GetRepositioriesUseCase
import com.marcin.domain.MainScreenState
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getRepositioriesUseCase: GetRepositioriesUseCase) : ViewModel() {

      internal var page = 1

      private val _state = MutableLiveData<MainScreenState>()
      val state: LiveData<MainScreenState>
            get() = _state

      fun fetchRepos() {
            val currentRepos = _state.value?.repositories ?: emptyList()

            _state.value = MainScreenState(repositories = currentRepos, loading = true)

            getRepositioriesUseCase.execute()
                  .subscribe(
                        { _state.value = MainScreenState(repositories = it) },
                        { _state.value = MainScreenState(error = it) }
                  )
      }

      fun fetchReposFurtherPage() {
            page++

            val currentRepos = _state.value?.repositories ?: emptyList()

            _state.value = MainScreenState(repositories = currentRepos, loading = true)

            getRepositioriesUseCase.execute(page)
                  .subscribe(
                        { _state.value = MainScreenState(repositories = currentRepos + it) },
                        { _state.value = MainScreenState(error = it) }
                  )
      }

}