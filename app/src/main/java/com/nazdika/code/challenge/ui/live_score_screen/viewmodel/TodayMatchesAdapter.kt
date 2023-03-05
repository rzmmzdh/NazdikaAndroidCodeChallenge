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
import com.nazdika.code.challenge.R
import com.nazdika.code.challenge.databinding.ItemCompetitionBinding
import com.nazdika.code.challenge.databinding.ItemMatchBinding
import com.nazdika.code.challenge.model.CompetitionMatch
import com.nazdika.code.challenge.model.ItemType
import com.nazdika.code.challenge.model.MatchModel

class TodayMatchesAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: MutableList<ItemType> = ArrayList()
    fun addItems(items: List<ItemType>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].itemType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> {
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
        if (items[position].itemType == 0) {
            val (_, persianName, logo, localizedName) = items[position] as CompetitionMatch
            val viewHolder = holder as CompetitionMatchViewHolder
            if (persianName != null) {
                viewHolder.binding.tvCompetitionName.text = persianName
            } else {
                viewHolder.binding.tvCompetitionName.text = localizedName
            }
            viewHolder.binding.tvCompetitionName.typeface = ResourcesCompat.getFont(
                viewHolder.itemView.context,
                R.font.vazir_medium
            )
            val uri = Uri.parse(logo)
            holder.binding.imgLogo.setImageURI(uri)
        } else if (items[position].itemType == 1) {
            val viewHolder = holder as MatchViewHolder
            val (_, _, _, _, _, matchStarted, _, _, _, matchEnded, _, _, _, status, homeTeam, awayTeam) = items[position] as MatchModel
            viewHolder.binding.tvAwayTeamName.typeface = ResourcesCompat.getFont(
                viewHolder.itemView.context,
                R.font.vazir_light
            )
            viewHolder.binding.tvHomeTeamName.typeface = ResourcesCompat.getFont(
                viewHolder.itemView.context,
                R.font.vazir_light
            )
            viewHolder.binding.imgAwayTemLogo.setImageURI(Uri.parse(awayTeam!!.logo))
            viewHolder.binding.imgHomeTeamLogo.setImageURI(Uri.parse(homeTeam!!.logo))
            if (homeTeam.persianName != null) {
                viewHolder.binding.tvHomeTeamName.text = homeTeam.persianName
            } else {
                viewHolder.binding.tvHomeTeamName.text = homeTeam.localizedName
            }
            if (awayTeam.persianName != null) {
                viewHolder.binding.tvAwayTeamName.text = awayTeam.persianName
            } else {
                viewHolder.binding.tvAwayTeamName.text = awayTeam.localizedName
            }
            if (matchStarted == false && (matchEnded == false || matchEnded == true)) {
                viewHolder.binding.tvStatus.visibility = View.GONE
                viewHolder.binding.tvStatus.updatePadding(
                    top = dpToPx(8f).toInt()
                )
                viewHolder.binding.tvScores.text = status
                viewHolder.binding.tvScores.setTextColor(context.resources.getColor(R.color.gray))
            } else {
                viewHolder.binding.tvStatus.visibility = View.VISIBLE
            }
        }
    }

    private fun dpToPx(dp: Float): Float {
        val r = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            r.displayMetrics
        )
    }

    override fun getItemCount(): Int {
        return items.size
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