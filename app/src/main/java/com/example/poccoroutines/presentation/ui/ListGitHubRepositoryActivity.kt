package com.example.poccoroutines.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.poccoroutines.data.model.RepositoryResponse
import com.example.poccoroutines.databinding.ActivityMainBinding
import com.example.poccoroutines.presentation.viewmodel.GitHubRepositoryViewModel
import com.example.poccoroutines.presentation.viewmodel.state.GitHubRepositoryState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListGitHubRepositoryActivity : AppCompatActivity() {

    private val viewModel: GitHubRepositoryViewModel by viewModel()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val listAdapter = RepositoryAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.getListRepositoryCoroutines()
        viewModel.getListRepositoryFlow()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getState().collect { state ->
                    setupState(state)
                }
            }
        }
    }

    private fun setupState(state: GitHubRepositoryState) {
        binding.progressBar.isVisible = state.isLoading
        binding.rvRepository.isVisible = state.isShowList
        state.list?.let { setupAdapter(it) }
    }

    private fun setupAdapter(list: List<RepositoryResponse>) {
        listAdapter.submitList(list)
        binding.rvRepository.adapter = listAdapter
    }


    private inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T
    ) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }
}