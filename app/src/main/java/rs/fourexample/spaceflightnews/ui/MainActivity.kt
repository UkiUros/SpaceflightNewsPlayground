package rs.fourexample.spaceflightnews.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import rs.fourexample.spaceflightnews.databinding.ActivityMainBinding
import rs.fourexample.spaceflightnews.utils.DataState

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ArticleAdapter.IArticleCallback {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArticleAdapter()
        adapter.callback = this
        binding.recyclerViewArticles.adapter = adapter
        subscribeToObservables()
        viewModel.loadArticles()
        binding.swipeRefreshLayout.isRefreshing = true
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadArticles()
        }
    }

    private fun subscribeToObservables() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    adapter.submitList(dataState.data)
                }
                is DataState.Error -> {
                    displayError()
                }
                DataState.Loading -> {
                    if (!binding.swipeRefreshLayout.isRefreshing) {
                        binding.swipeRefreshLayout.isRefreshing = true
                    }
                }
            }
        })
    }


    private fun displayError() {
        if (binding.swipeRefreshLayout.isRefreshing) {
            binding.swipeRefreshLayout.isRefreshing = false
        }
        Toast.makeText(
            this,
            "Ooops, some error occured!",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onItemClick(url: String) {
        startActivity(Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        ))
    }
}
