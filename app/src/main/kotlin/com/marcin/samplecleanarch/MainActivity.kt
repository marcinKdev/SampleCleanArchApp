package com.marcin.samplecleanarch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.marcin.samplecleanarch.ui.GithubReposScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

      private val viewModel: MainViewModel by viewModels()

      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            viewModel.fetchRepos()

            setContent {
                  val uiState = viewModel.state.collectAsState().value
                  GithubReposScreen(
                        uiState,
                        onTryAgainClicked = { viewModel.fetchRepos() },
                        onFetchMoreReposClicked = { viewModel.fetchReposFurtherPage() })
            }
      }
}