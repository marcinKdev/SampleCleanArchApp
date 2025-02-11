package com.marcin.samplecleanarch

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.marcin.domain.MainScreenState
import com.marcin.samplecleanarch.ui.GithubReposScreen
import com.marcin.testdata.RepositoriesTestData
import org.junit.Rule
import org.junit.Test

class GithubReposScreenTest {

      @get:Rule
      val composeTestRule = createComposeRule()

      @Test
      fun loadingCircleTest() {
            composeTestRule.setContent {
                  GithubReposScreen(MainScreenState(loading = true), {}, {})
            }

            composeTestRule.onNodeWithTag("LoadingCircle").assertIsDisplayed()
      }

      @Test
      fun sampleScreenStateTest() {
            composeTestRule.setContent {
                  GithubReposScreen(MainScreenState(repositories = listOf(RepositoriesTestData.repo1, RepositoriesTestData.repo2)), {}, {})
            }

            composeTestRule.onNodeWithText("The Kotlin Programming Language. ").assertIsDisplayed()
      }

      @Test
      fun sampleErrorScreenStateTest() {
            composeTestRule.setContent {
                  GithubReposScreen(MainScreenState(error = Exception()), {}, {})
            }

            composeTestRule.onNodeWithText("TRY AGAIN").assertIsDisplayed()
      }
}