package org.joe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.joe.Data.Section;
import org.joe.R;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    List<Section> sections;
    private OnSectionSelectedListener listener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public SectionAdapter( List<Section> sections, OnSectionSelectedListener listener) {
        this.sections = sections;
        this.listener = listener;

    }



    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.sections_layout, null);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        Section section = sections.get(position);


        holder.title.setText(section.getTitle());
        holder.subtitle.setText(section.getSubtitle());


        Glide.with(holder.itemView.getContext())
                .load(getImageResource(holder.itemView.getContext(), section.getImage()))
                .into(holder.image);


        int backgroundColor = position == selectedPosition
                ? ContextCompat.getColor(holder.itemView.getContext(), android.R.color.holo_orange_light) // Yellow for selected
                : ContextCompat.getColor(holder.itemView.getContext(), android.R.color.black); // Black for unselected
        holder.layout.setBackgroundColor(backgroundColor);


        holder.itemView.setOnClickListener(v -> {

            int previousPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();


            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);

            if (listener != null) {
                listener.onSectionSelected(section);
            }
        });
    }




    private int getImageResource(Context context, String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    public interface OnSectionSelectedListener {
        void onSectionSelected(Section Section);
    }

    public void updateSections(List<Section> newSections) {
        this.sections = newSections;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return sections.size();
    }

    static class SectionViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView subtitle;
        private final ImageView image;
        private final ConstraintLayout layout;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.sectionTitleView);
            image = itemView.findViewById(R.id.sectionimageView);
            layout = itemView.findViewById(R.id.sectionConstraintLayout);
            subtitle = itemView.findViewById(R.id.sectionSubtitleView);
        }
    }
}

