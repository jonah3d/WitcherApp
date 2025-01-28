package org.joe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.joe.Data.Ingredient;
import org.joe.Data.IngredientWithQuantity;
import org.joe.Data.Recipe;
import org.joe.R;

import java.util.List;

public class IngredientAdaptor extends RecyclerView.Adapter<IngredientAdaptor.IngredientViewHolder> {

    private List<Ingredient> ingredients;
    private onIngredientsSelectedListener listener;

    private int selectedPosition = RecyclerView.NO_POSITION;


    public IngredientAdaptor(List<Ingredient> ingredients, onIngredientsSelectedListener listener) {
        this.ingredients = ingredients;
        this.listener = listener;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.ingredients_all_layout, null);
        return new IngredientViewHolder(view);

    }
    public Ingredient getSelectedIngredient() {
        return ingredients.get(selectedPosition);
    }
    public int getSelectedPosition() {
        return selectedPosition;
    }



    public interface onIngredientsSelectedListener {
        void onIngredientsSelected(Ingredient ingredient);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {

        Ingredient ingredient = ingredients.get(position);

        holder.name.setText(ingredient.getName());
        holder.ingredientDescription.setText(ingredient.getDescription());
        holder.image.setImageResource(getImageResource(holder.itemView.getContext(), ingredient.getImage()));


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
                listener.onIngredientsSelected(ingredient);
            }
        });
    }
    private int getImageResource(Context context, String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView ingredientDescription;
        ImageView image;
        MaterialCardView layout;
        public IngredientViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.cardView);
            name = itemView.findViewById(R.id.Tv_ingreientname);
            ingredientDescription = itemView.findViewById(R.id.Tv_ingredientdescription);
            image = itemView.findViewById(R.id.Iv_ingredientimage);
        }
    }
}
