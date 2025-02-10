package com.marcin.samplecleanarch

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.marcin.domain.MainScreenState
import com.marcin.samplecleanarch.databinding.ActivityMainBinding
import com.marcin.samplecleanarch.ui.GithubReposScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

      private val viewModel: MainViewModel by viewModels()

      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            viewModel.fetchRepos()

            setContent {
                  val uiState = viewModel.state.collectAsState().value
                  GithubReposScreen(uiState, onTryAgainClicked = { viewModel.fetchRepos() }, onFetchMoreReposClicked = { viewModel.fetchReposFurtherPage() })
            }
      }
}