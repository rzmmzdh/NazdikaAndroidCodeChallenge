package com.nazdika.code.challenge.ui.live_score_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nazdika.code.challenge.databinding.FragmentLiveScoreBinding
import com.nazdika.code.challenge.ui.live_score_screen.viewmodel.LiveScoreUiState
import com.nazdika.code.challenge.ui.live_score_screen.viewmodel.LiveScoreViewModel
import com.nazdika.code.challenge.ui.live_score_screen.viewmodel.TodayMatchesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LiveScoreFragment : Fragment() {
    private var _binding: FragmentLiveScoreBinding? = null
    private val binding get() = _binding!!
    private lateinit var todayMatchesAdapter: TodayMatchesAdapter
    private val viewModel: LiveScoreViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLiveScoreBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                updateUi(it)
            }

        }
        initRecyclerView()
        return binding.root
    }

    private fun updateUi(it: LiveScoreUiState) {
        if (it.data.isNotEmpty()) {
            binding.progressbar.visibility = View.GONE
            todayMatchesAdapter.addItems(buildList {
                it.data.forEach { match ->
                    add(match)
                    match.matches?.let { matches -> addAll(matches) }
                }
            })
        }
        if (it.isLoading) {
            binding.progressbar.visibility = View.VISIBLE
        } else if (it.error != null) {
            binding.progressbar.visibility = View.GONE
            Toast.makeText(
                this@LiveScoreFragment.context,
                "${it.error}",
                Toast.LENGTH_LONG
            )
                .show()

        }
    }

    private fun initRecyclerView() {
        todayMatchesAdapter =
            TodayMatchesAdapter(
                this@LiveScoreFragment.context
            )
        binding.rvTodayMatches.layoutManager =
            LinearLayoutManager(this@LiveScoreFragment.context, RecyclerView.VERTICAL, false)
        binding.rvTodayMatches.adapter = todayMatchesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
