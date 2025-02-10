package com.marcin.samplecleanarch

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.marcin.RepositoriesAdapter
import com.marcin.domain.MainScreenState
import com.marcin.samplecleanarch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

      private val viewModel: MainViewModel by viewModels()

      val repositoriesAdapter = RepositoriesAdapter()

      internal lateinit var binding: ActivityMainBinding

      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.tryAgainButton.setOnClickListener { viewModel.fetchRepos() }

            viewModel.state.observe(this, {
                  renderState(it)
            })

            viewModel.fetchRepos()

            binding.repositoriesRecycler.adapter = repositoriesAdapter

            binding.repositoriesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                  override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)

                        if (recyclerView.canScrollVertically(1).not() && newState == RecyclerView.SCROLL_STATE_IDLE) {
                              viewModel.fetchReposFurtherPage()
                        }
                  }

            })
      }

      internal fun renderState(state: MainScreenState) {
            binding.progress.isVisible = state.loading || (state.repositories.isEmpty() && state.error == null)
            binding.tryAgainButton.isVisible = state.error != null

            if (state.repositories.isNotEmpty()) {
                  repositoriesAdapter.submitList(state.repositories)
            }
      }
}