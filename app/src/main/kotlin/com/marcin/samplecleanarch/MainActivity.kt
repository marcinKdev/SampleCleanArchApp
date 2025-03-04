package com.marcin.samplecleanarch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marcin.samplecleanarch.ui.GithubRepoDetailScreen
import com.marcin.samplecleanarch.ui.GithubRepoDetails
import com.marcin.samplecleanarch.ui.GithubRepos
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

                  val navController = rememberNavController()

                  NavHost(
                        navController = navController,
                        startDestination = GithubRepos
                  ) {
                        composable<GithubRepos> {
                              GithubReposScreen(
                                    uiState,
                                    onTryAgainClicked = { viewModel.fetchRepos() },
                                    onFetchMoreReposClicked = { viewModel.fetchReposFurtherPage() },
                                    onNavigateToRepoDetails = { navController.navigate(route = GithubRepoDetails) }
                              )
                        }
                        composable<GithubRepoDetails> { GithubRepoDetailScreen() }
                  }
            }
      }
}