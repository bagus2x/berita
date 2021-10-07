package com.tubagus.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tubagus.news.adapter.GlobalHeadlinesAdapter
import com.tubagus.news.adapter.LocalHeadlinesAdapter
import com.tubagus.news.databinding.ActivityMainBinding
import com.tubagus.news.utlities.StateResult
import com.tubagus.news.utlities.makeSnackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGlobalHeadlines()
        setupLocalHeadlines()
    }

    private fun setupGlobalHeadlines() {
        mainViewModel.getGlobalHeadlines(5)
        val globalHeadlinesAdapter = GlobalHeadlinesAdapter()
        mainViewModel.headlinesGlobal.observe(this, { res ->
            when (res) {
                is StateResult.Loading -> {
                }
                is StateResult.Success -> {
                    res.data?.let { it ->
                        globalHeadlinesAdapter.differ.submitList(it.articles)
                    }
                }
                is StateResult.Error -> {
                    makeSnackbar(res.message ?: "Error has occurred", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

        binding.rvHeadlinesGlobal.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = globalHeadlinesAdapter
        }
    }

    private fun setupLocalHeadlines() {
        mainViewModel.getLocalHeadlines(20)
        val localHeadlinesAdapter = LocalHeadlinesAdapter()
        mainViewModel.headlinesLocal.observe(this, { res ->
            when (res) {
                is StateResult.Loading -> {
                }
                is StateResult.Success -> {
                    res.data?.let { it ->
                        localHeadlinesAdapter.differ.submitList(it.articles)
                    }
                }
                is StateResult.Error -> {
                    makeSnackbar(res.message ?: "Error has occurred", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

        binding.rvHeadlinesLocal.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = localHeadlinesAdapter
        }
    }
}