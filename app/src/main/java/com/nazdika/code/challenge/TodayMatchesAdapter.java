package com.nazdika.code.challenge;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nazdika.code.challenge.databinding.ItemCompetitionBinding;
import com.nazdika.code.challenge.databinding.ItemMatchBinding;

import java.util.ArrayList;
import java.util.List;

public class TodayMatchesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<MainActivity.ItemType> items = new ArrayList<>();

    public void addItems(List<MainActivity.ItemType> items) {
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
            MainActivity.CompetitionMatchModel model = (MainActivity.CompetitionMatchModel) items.get(position);
            CompetitionMatchViewHolder viewHolder = (CompetitionMatchViewHolder) holder;
            if (model.getPersianName() != null) {
                viewHolder.binding.tvCompetitionName.setText(model.getPersianName());
            } else {
                viewHolder.binding.tvCompetitionName.setText(model.getLocalizedName());
            }
            viewHolder.binding.tvCompetitionName.setTypeface(ResourcesCompat.getFont(viewHolder.itemView.getContext(), R.font.vazir_bold));
            Uri uri = Uri.parse(model.getLogo());
            ((CompetitionMatchViewHolder) holder).binding.imgLogo.setImageURI(uri);
        } else if (items.get(position).getItemType() == 1) {
            MatchViewHolder viewHolder = (MatchViewHolder) holder;
        }
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
