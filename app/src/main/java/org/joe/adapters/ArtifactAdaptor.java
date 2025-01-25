package org.joe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.joe.Data.Artifact;
import org.joe.R;

import java.util.List;

public class ArtifactAdaptor extends RecyclerView.Adapter<ArtifactAdaptor.ArtifactViewHolder> {

    private List<Artifact> artifacts;
    private final OnArtifactSelectedListener listener;
    private int selectedPosition = RecyclerView.NO_POSITION;


    public interface OnArtifactSelectedListener {
        void onArtifactSelected(Artifact artifact);
    }

    public ArtifactAdaptor(List<Artifact> artifacts, OnArtifactSelectedListener listener) {
        this.artifacts = artifacts;
        this.listener = listener;
    }


    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArtifactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.artefact_layout, null);
        return new ArtifactViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ArtifactViewHolder holder, int position) {
        Artifact artifact = artifacts.get(position);

        holder.name.setText(artifact.getName());
        holder.effectDescription.setText(artifact.getEffect());
        holder.image.setImageResource(getImageResource(holder.itemView.getContext(), artifact.getImage()));

        int backgroundColor = position == selectedPosition
                ? holder.itemView.getContext().getColor(android.R.color.holo_orange_light)
                : holder.itemView.getContext().getColor(android.R.color.white);
        holder.outerCard.setStrokeColor(backgroundColor);


        int backgroundColor2 = position == selectedPosition
                ? holder.itemView.getContext().getColor(android.R.color.holo_orange_dark)
                : holder.itemView.getContext().getColor(android.R.color.white);

        holder.innerCard.setStrokeColor(backgroundColor2);

        holder.itemView.setOnClickListener(v -> {
            int previousPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();


            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);

            if (listener != null) {
                listener.onArtifactSelected(artifact);
            }
        });

    }

    private int getImageResource(Context context, String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    @Override
    public int getItemCount() {
        return artifacts.size();
    }

    static class ArtifactViewHolder extends RecyclerView.ViewHolder {


        TextView name;
        TextView effectDescription;
        AppCompatImageView image;

        MaterialCardView outerCard;
        MaterialCardView innerCard;

        public ArtifactViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.artefactTitleView);
            effectDescription = itemView.findViewById(R.id.artefactDescriptionView);
            image = itemView.findViewById(R.id.artefactimageView);
            outerCard = itemView.findViewById(R.id.artefactouterCardView);
            innerCard = itemView.findViewById(R.id.artefactinnerCardView);
        }
    }
}
