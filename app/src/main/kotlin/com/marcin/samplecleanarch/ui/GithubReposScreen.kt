package com.marcin.samplecleanarch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcin.domain.MainScreenState
import com.marcin.samplecleanarch.R


@Composable
fun GithubReposScreen(
      state: MainScreenState,
      onTryAgainClicked: () -> Unit = {},
      onFetchMoreReposClicked: () -> Unit = {}
) {
      val listState = rememberLazyListState()
      val buffer = 1
      val reachedBottomOfTheList: Boolean by remember {
            derivedStateOf {
                  val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
                  lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
            }
      }
      LaunchedEffect(reachedBottomOfTheList) {
            if (reachedBottomOfTheList) {
                  onFetchMoreReposClicked()
            }
      }

      Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
      ) {
            when {
                  state.loading -> {
                        CircularProgressIndicator()
                  }

                  state.error != null -> {
                        Button(
                              onClick = { onTryAgainClicked() },
                              modifier = Modifier.padding(32.dp)
                        ) {
                              Text(text = stringResource(id = R.string.buttonLabel))
                        }
                  }

                  else -> {
                        LazyColumn(state = listState) {
                              state.repositories.forEachIndexed { index, it ->
                                    item { RepositoryCard(index.toString(), it.description.orEmpty()) }
                              }
                        }

                        if (state.loadingMore) {
                              Box(
                                    modifier = Modifier
                                          .fillMaxSize()
                                          .background(Color.Black.copy(alpha = 0.3f)),
                                    contentAlignment = Alignment.Center
                              ) {
                                    CircularProgressIndicator()
                              }
                        }
                  }
            }
      }
}

@Composable
fun RepositoryCard(repositoryName: String, repositoryDesc: String) {
      Card(
            modifier = Modifier
                  .fillMaxWidth()
                  .padding(10.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
      ) {
            Column(
                  modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
            ) {
                  Text(
                        text = stringResource(id = R.string.repository_name),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 10.dp)
                  )
                  Text(
                        text = repositoryName,
                        modifier = Modifier.padding(bottom = 10.dp)
                  )
                  Text(
                        text = stringResource(id = R.string.repository_description),
                        fontWeight = FontWeight.Bold
                  )
                  Text(
                        text = repositoryDesc
                  )
            }
      }
}

@Preview
@Composable
fun ReposPreview() {
      val stateLoading = MainScreenState(loading = true)
      GithubReposScreen(stateLoading)
}