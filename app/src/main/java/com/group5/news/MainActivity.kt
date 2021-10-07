package com.group5.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.group5.news.adapter.TechnologyHeadlinesAdapter
import com.group5.news.adapter.GeneralHeadlinesAdapter
import com.group5.news.databinding.ActivityMainBinding
import com.group5.news.utlities.StateResult
import com.group5.news.utlities.makeSnackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTechnologyHeadlines()
        setupGeneralHeadlines()
    }

    private fun setupTechnologyHeadlines() {
        mainViewModel.getTechnologyHeadlines(5)
        val technologyHeadlinesAdapter = TechnologyHeadlinesAdapter()
        mainViewModel.headlinesTechnology.observe(this, { res ->
            when (res) {
                is StateResult.Loading -> {
                }
                is StateResult.Success -> {
                    res.data?.let { it ->
                        technologyHeadlinesAdapter.differ.submitList(it.articles)
                    }
                }
                is StateResult.Error -> {
                    makeSnackbar(res.message ?: "Error has occurred", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

        binding.rvHeadlinesTechnology.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = technologyHeadlinesAdapter
        }
    }

    private fun setupGeneralHeadlines() {
        mainViewModel.getGeneralHeadlines(20)
        val generalHeadlinesAdapter = GeneralHeadlinesAdapter()
        mainViewModel.headlinesGeneral.observe(this, { res ->
            when (res) {
                is StateResult.Loading -> {
                }
                is StateResult.Success -> {
                    res.data?.let { it ->
                        generalHeadlinesAdapter.differ.submitList(it.articles)
                    }
                }
                is StateResult.Error -> {
                    makeSnackbar(res.message ?: "Error has occurred", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

        binding.rvHeadlinesGeneral.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = generalHeadlinesAdapter
        }
    }
}