package org.joe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.joe.Data.Ingredient;
import org.joe.R;

import java.util.List;

public class IngredientAdaptor extends RecyclerView.Adapter<IngredientAdaptor.IngredientViewHolder> {

    private List<Ingredient> ingredients;


    public IngredientAdaptor(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {

        Ingredient ingredient = ingredients.get(position);

        holder.name.setText(ingredient.getName());
        holder.ingredientDescription.setText(ingredient.getDescription());
        holder.image.setImageResource(getImageResource(holder.itemView.getContext(), ingredient.getImage()));

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
        public IngredientViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Tv_ingreientname);
            ingredientDescription = itemView.findViewById(R.id.Tv_ingredientdescription);
            image = itemView.findViewById(R.id.Iv_ingredientimage);
        }
    }
}
