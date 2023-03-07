package com.nazdika.code.challenge.ui.live_score_screen.viewmodel

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nazdika.code.challenge.R
import com.nazdika.code.challenge.databinding.ItemCompetitionBinding
import com.nazdika.code.challenge.databinding.ItemMatchBinding
import com.nazdika.code.challenge.model.Competition
import com.nazdika.code.challenge.model.ItemType
import com.nazdika.code.challenge.model.Match
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TodayMatchesAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val COMPETITION_TYPE = 0
        private const val MATCH_TYPE = 1
    }

    private val matches = mutableListOf<ItemType>()
    fun addItems(matches: List<ItemType> = emptyList()) {
        this.matches.clear()
        this.matches.addAll(matches)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return matches[position].itemType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            COMPETITION_TYPE -> {
                CompetitionMatchViewHolder(
                    layoutInflater.inflate(
                        R.layout.item_competition,
                        parent,
                        false
                    )
                )
            }

            else -> {
                MatchViewHolder(layoutInflater.inflate(R.layout.item_match, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (matches[position].itemType == COMPETITION_TYPE) {
            val competitionVH = holder as CompetitionMatchViewHolder
            val competition = matches[position] as Competition
            competitionVH.binding.tvCompetitionName.apply {
                if (competition.persianName != null) text =
                    competition.persianName else competition.localizedName
                typeface = ResourcesCompat.getFont(
                    competitionVH.itemView.context,
                    R.font.vazir_medium
                )
                Glide.with(holder.itemView.context).load(competition.logo)
                    .into(holder.binding.imgLogo)
            }
        } else if (matches[position].itemType == MATCH_TYPE) {
            val matchVH = holder as MatchViewHolder
            val match = matches[position] as Match
            matchVH.binding.tvAwayTeamName.apply {
                text = match.awayTeam?.persianName ?: match.awayTeam?.localizedName
                typeface = ResourcesCompat.getFont(
                    matchVH.itemView.context,
                    R.font.vazir_light
                )
            }
            matchVH.binding.tvHomeTeamName.apply {
                text = match.homeTeam?.persianName ?: match.homeTeam?.localizedName
                typeface = ResourcesCompat.getFont(
                    matchVH.itemView.context,
                    R.font.vazir_light
                )
            }
            Glide.with(holder.itemView.context).load(Uri.parse(match.awayTeam!!.logo))
                .into(matchVH.binding.imgAwayTemLogo)
            Glide.with(holder.itemView.context).load(Uri.parse(match.homeTeam!!.logo))
                .into(matchVH.binding.imgHomeTeamLogo)
            if (match.matchEnded == true) {
                matchVH.binding.tvStatus.apply {
                    text = match.status
                    typeface = ResourcesCompat.getFont(
                        matchVH.itemView.context,
                        R.font.vazir_light
                    )

                }
            } else {
                matchVH.binding.tvStatus.apply {
                    val startDate = timestampToLocalTime(match.timestamp)
                    text = startDate
                    typeface = ResourcesCompat.getFont(
                        matchVH.itemView.context,
                        R.font.vazir_light
                    )
                }
            }
            if (match.matchStarted == false) {
                matchVH.binding.tvScores.visibility = View.GONE
            }
            matchVH.binding.tvScores.apply {
                text = context.getString(
                    R.string.scores,
                    match.awayTeamScore.toString(),
                    match.homeTeamScore.toString()
                )
                typeface = ResourcesCompat.getFont(
                    matchVH.itemView.context,
                    R.font.vazir_medium
                )

            }
        }
    }

    private fun timestampToLocalTime(time: Long): String? {
        return try {
            val format = SimpleDateFormat("HH:mm", Locale.getDefault())
            val timestamp = Date(time * 1000)
            format.format(timestamp)
        } catch (e: Exception) {
            e.toString()
        }
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    inner class CompetitionMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemCompetitionBinding

        init {
            binding = ItemCompetitionBinding.bind(itemView)
        }
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemMatchBinding

        init {
            binding = ItemMatchBinding.bind(itemView)
        }
    }
}