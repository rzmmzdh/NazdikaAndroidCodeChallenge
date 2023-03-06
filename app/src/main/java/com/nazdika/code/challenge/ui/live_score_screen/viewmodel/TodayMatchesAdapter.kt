package com.nazdika.code.challenge.ui.live_score_screen.viewmodel

import android.content.Context
import android.net.Uri
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nazdika.code.challenge.R
import com.nazdika.code.challenge.databinding.ItemCompetitionBinding
import com.nazdika.code.challenge.databinding.ItemMatchBinding
import com.nazdika.code.challenge.model.CompetitionMatch
import com.nazdika.code.challenge.model.ItemType
import com.nazdika.code.challenge.model.MatchModel

class TodayMatchesAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val COMPETITION_TYPE = 0
        private const val MATCH_TYPE = 1
    }

    private val matches = mutableListOf<ItemType>()
    fun addItems(matches: List<ItemType>) {
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
            val competition = matches[position] as CompetitionMatch
            val competitionVH = holder as CompetitionMatchViewHolder
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
            val match = matches[position] as MatchModel
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

            if (match.matchStarted == false && (match.matchEnded == false || match.matchEnded == true)) {
                matchVH.binding.tvStatus.apply {
                    visibility = View.GONE
                    updatePadding(top = dpToPx(8f).toInt())
                }
                matchVH.binding.tvScores.apply {
                    text = match.status
                    setTextColor(context.resources.getColor(R.color.gray))
                }
            } else {
                matchVH.binding.tvStatus.visibility = View.VISIBLE
            }
        }
    }

    private fun dpToPx(dp: Float): Float {
        val resources = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        )
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