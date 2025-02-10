/*
package com.marcin.samplecleanarch

import android.os.Looper
import android.view.View
import com.marcin.RepositoryViewHolder
import com.marcin.domain.MainScreenState
import com.marcin.testdata.RepositoriesTestData
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
class MainActivityRobolectricTest {

      private val activityController: ActivityController<MainActivity> = Robolectric.buildActivity(MainActivity::class.java)

      private lateinit var activity: MainActivity

      @Before
      fun setup() {
            activity = activityController.get()
            activityController.setup()
      }

      @Test
      fun `Screen state is rendered correct on loading`() {
            activity.renderState(MainScreenState(loading = true))

            assertThat(activity.binding.progress.visibility, equalTo(View.VISIBLE))
            assertThat(activity.binding.tryAgainButton.visibility, equalTo(View.GONE))
      }

      @Test
      fun `Screen state is rendered correct on error`() {
            activity.renderState(MainScreenState(error = Throwable()))

            assertThat(activity.binding.progress.visibility, equalTo(View.GONE))
            assertThat(activity.binding.tryAgainButton.visibility, equalTo(View.VISIBLE))
      }

      @Test
      fun `Screen state is rendered correct when real data items are returned`() {
            val testRepos = listOf(RepositoriesTestData.repo1, RepositoriesTestData.repo2, RepositoriesTestData.repo3)

            activity.renderState(MainScreenState(repositories = testRepos))

            assertThat(activity.binding.progress.visibility, equalTo(View.GONE))
            assertThat(activity.binding.tryAgainButton.visibility, equalTo(View.GONE))

            val repositoriesRecycler = activity.binding.repositoriesRecycler

            assertThat(repositoriesRecycler.adapter?.itemCount, equalTo(testRepos.size))

            repositoriesRecycler.scrollToPosition(0)

            Shadows.shadowOf(Looper.getMainLooper()).idle()

            val repo1View = (repositoriesRecycler.findViewHolderForAdapterPosition(0) as RepositoryViewHolder).viewholderRepositoryBinding

            assertThat(repo1View.repositoryName.text.toString(), equalTo(testRepos[0].fullName))
            assertThat(repo1View.repositoryDesc.text.toString(), equalTo(testRepos[0].description))

            repositoriesRecycler.scrollToPosition(1)

            Shadows.shadowOf(Looper.getMainLooper()).idle()

            val repo2View = (repositoriesRecycler.findViewHolderForAdapterPosition(1) as RepositoryViewHolder).viewholderRepositoryBinding

            assertThat(repo2View.repositoryName.text.toString(), equalTo(testRepos[1].fullName))
            assertThat(repo2View.repositoryDesc.text.toString(), equalTo(testRepos[1].description))

            repositoriesRecycler.scrollToPosition(2)

            Shadows.shadowOf(Looper.getMainLooper()).idle()

            val repo3View = (repositoriesRecycler.findViewHolderForAdapterPosition(2) as RepositoryViewHolder).viewholderRepositoryBinding

            assertThat(repo3View.repositoryName.text.toString(), equalTo(testRepos[2].fullName))
            assertThat(repo3View.repositoryDesc.text.toString(), equalTo(testRepos[2].description))
      }


}*/
