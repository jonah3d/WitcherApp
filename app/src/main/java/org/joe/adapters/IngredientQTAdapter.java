package org.joe.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import org.joe.Data.IngredientWithQuantity;
import org.joe.R;

import java.util.List;

public class IngredientQTAdapter  extends RecyclerView.Adapter<IngredientQTAdapter.IngredientQTViewHolder> {

    List<IngredientWithQuantity> ingredients;

    public IngredientQTAdapter(List<IngredientWithQuantity> ingredients) {
        this.ingredients = ingredients;
    }

   public void setIngredients(List<IngredientWithQuantity> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientQTViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.ingredients, null);
        return new IngredientQTAdapter.IngredientQTViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull IngredientQTViewHolder holder, int position) {

            IngredientWithQuantity ingredient = ingredients.get(position);

            holder.name.setText(ingredient.getIngredient_id());
            holder.quantity.setText(String.valueOf(ingredient.getQuantity()));
            holder.image.setImageResource(getImageResource(holder.itemView.getContext(), ingredient.getIngredient_image()));

    }
    private int getImageResource(Context context, String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class IngredientQTViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        AppCompatTextView quantity;
        public IngredientQTViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById( R.id.imgv_ingredient);
            name = itemView.findViewById(R.id.tv_ingredient_name);
            quantity = itemView.findViewById(R.id.tv_ingredientqt);
        }
    }
}
