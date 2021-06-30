package com.marcin.samplecleanarch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.marcin.RepositoriesAdapter
import com.marcin.domain.MainScreenState
import com.marcin.samplecleanarch.databinding.ActivityMainBinding
import com.marcin.samplecleanarch.di.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

      @Inject
      lateinit var vmFactory: ViewModelFactory<MainViewModel>

      lateinit var viewModel: MainViewModel

      val repositoriesAdapter = RepositoriesAdapter()

      internal lateinit var binding: ActivityMainBinding

      override fun onCreate(savedInstanceState: Bundle?) {
            AndroidInjection.inject(this)
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.tryAgainButton.setOnClickListener { viewModel.fetchRepos() }

            viewModel = ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)

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

            if (state.repositories.isNotEmpty()) { repositoriesAdapter.submitList(state.repositories) }
      }
}