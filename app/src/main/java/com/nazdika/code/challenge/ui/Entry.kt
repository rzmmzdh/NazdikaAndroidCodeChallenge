package com.nazdika.code.challenge.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nazdika.code.challenge.databinding.ActivityMainBinding
import com.nazdika.code.challenge.ui.viewmodel.LiveScoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Entry : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todayMatchesAdapter: TodayMatchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        val viewModel: LiveScoreViewModel by viewModels()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    if (it.data!!.isNotEmpty()) {
                        binding.progressbar.visibility = View.GONE
                        todayMatchesAdapter.addItems(buildList {
                            it.data.forEach { match ->
                                add(match)
                                match?.matches?.let { matches -> addAll(matches) }
                            }
                        })
                    }
                    if (it.isLoading) {
                        binding.progressbar.visibility = View.VISIBLE
                    } else if (it.error != null) {
                        binding.progressbar.visibility = View.GONE
                        Toast.makeText(this@Entry, "${it.error}", Toast.LENGTH_LONG)
                            .show()

                    }
                }
            }

        }
    }

    private fun initRecyclerView() {
        todayMatchesAdapter =
            TodayMatchesAdapter(
                applicationContext
            )
        binding.rvTodayMatches.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvTodayMatches.adapter = todayMatchesAdapter
    }


}