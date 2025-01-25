package org.joe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import org.joe.Data.Entry;
import org.joe.R;
import org.joe.fragments.FragmentBestiaryInformacion;

import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {
    private List<Entry> entries;
    private onEntrySelectedListener listener;

    private int selectedPosition = RecyclerView.NO_POSITION;

    public EntryAdapter(List<Entry> entries, onEntrySelectedListener listener) {
        this.entries = entries;
        this.listener = listener;

    }

    public void updateEntries(List<Entry> entries) {
        if (entries != null) {
            this.entries.clear();
            this.entries.addAll(entries);
            notifyDataSetChanged();
            notifyItemChanged(selectedPosition);
        }

    }

    public interface onEntrySelectedListener {
        void onEntrySelected(Entry entry);
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.entries_layout, null);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
        Entry entry = entries.get(position);
        holder.titleTextView.setText(entry.getTitle());
        Glide.with(holder.itemView.getContext())
                .load(getImageResource(holder.itemView.getContext(), entry.getImage()))
                .into(holder.entryImageView);

        int backgroundColor = position == selectedPosition
                ? holder.itemView.getContext().getColor(android.R.color.holo_orange_light)
                : holder.itemView.getContext().getColor(android.R.color.white);
        holder.layout.setStrokeColor(backgroundColor);

        holder.itemView.setOnClickListener(v -> {
            int previousPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();

            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);

            if (listener != null) {
                listener.onEntrySelected(entry);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    private int getImageResource(Context context, String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView entryImageView;
        MaterialCardView layout;

        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.entriesTitleView);
            entryImageView = itemView.findViewById(R.id.EntriesReyclerView);
            layout = itemView.findViewById(R.id.entriesCardView);
        }
    }
}
