package com.nazdika.code.challenge.ui;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewKt;
import androidx.recyclerview.widget.RecyclerView;

import com.nazdika.code.challenge.R;
import com.nazdika.code.challenge.databinding.ItemCompetitionBinding;
import com.nazdika.code.challenge.databinding.ItemMatchBinding;
import com.nazdika.code.challenge.model.CompetitionMatch;
import com.nazdika.code.challenge.model.ItemType;
import com.nazdika.code.challenge.model.MatchModel;

import java.util.ArrayList;
import java.util.List;

public class TodayMatchesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ItemType> items = new ArrayList<>();
    private Context context;

    public TodayMatchesAdapter(Context context) {
        this.context = context;
    }

    public void addItems(List<ItemType> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getItemType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0: {
                return new CompetitionMatchViewHolder(layoutInflater.inflate(R.layout.item_competition, parent, false));
            }
            case 1: {
                return new MatchViewHolder(layoutInflater.inflate(R.layout.item_match, parent, false));
            }
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (items.get(position).getItemType() == 0) {
            CompetitionMatch competition = (CompetitionMatch) items.get(position);
            CompetitionMatchViewHolder viewHolder = (CompetitionMatchViewHolder) holder;
            if (competition.getPersianName() != null) {
                viewHolder.binding.tvCompetitionName.setText(competition.getPersianName());
            } else {
                viewHolder.binding.tvCompetitionName.setText(competition.getLocalizedName());
            }
            viewHolder.binding.tvCompetitionName.setTypeface(ResourcesCompat.getFont(viewHolder.itemView.getContext(), R.font.vazir_medium));
            Uri uri = Uri.parse(competition.getLogo());
            ((CompetitionMatchViewHolder) holder).binding.imgLogo.setImageURI(uri);
        } else if (items.get(position).getItemType() == 1) {
            MatchViewHolder viewHolder = (MatchViewHolder) holder;
            MatchModel match = (MatchModel) items.get(position);
            viewHolder.binding.tvAwayTeamName.setTypeface(ResourcesCompat.getFont(viewHolder.itemView.getContext(), R.font.vazir_light));
            viewHolder.binding.tvHomeTeamName.setTypeface(ResourcesCompat.getFont(viewHolder.itemView.getContext(), R.font.vazir_light));
            viewHolder.binding.imgAwayTemLogo.setImageURI(Uri.parse(match.getAwayTeam().getLogo()));
            viewHolder.binding.imgHomeTeamLogo.setImageURI(Uri.parse(match.getHomeTeam().getLogo()));
            if (match.getHomeTeam().getPersianName() != null) {
                viewHolder.binding.tvHomeTeamName.setText(match.getHomeTeam().getPersianName());
            } else {
                viewHolder.binding.tvHomeTeamName.setText(match.getHomeTeam().getLocalizedName());
            }

            if (match.getAwayTeam().getPersianName() != null) {
                viewHolder.binding.tvAwayTeamName.setText(match.getAwayTeam().getPersianName());
            } else {
                viewHolder.binding.tvAwayTeamName.setText(match.getAwayTeam().getLocalizedName());
            }

            if (match.getMatchStarted() == false && (match.getMatchEnded() == false || match.getMatchEnded() == true)) {
                viewHolder.binding.tvStatus.setVisibility(View.GONE);
                ViewKt.updateLayoutParams(viewHolder.binding.tvStatus, layoutParams -> {
                    ((ConstraintLayout.LayoutParams) layoutParams).topMargin = (int) dpToPx(8f);
                    return null;
                });
                viewHolder.binding.tvScores.setText(match.getStatus());
                viewHolder.binding.tvScores.setTextColor(context.getResources().getColor(R.color.gray));
            } else {
                viewHolder.binding.tvStatus.setVisibility(View.VISIBLE);
            }
        }
    }

    private float dpToPx(float dp) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CompetitionMatchViewHolder extends RecyclerView.ViewHolder {
        final ItemCompetitionBinding binding;

        public CompetitionMatchViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCompetitionBinding.bind(itemView);
        }
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder {
        final ItemMatchBinding binding;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemMatchBinding.bind(itemView);
        }
    }
}
