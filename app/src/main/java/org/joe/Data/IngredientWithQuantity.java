package org.joe.Data;

public class IngredientWithQuantity {
    public Integer recipe_id;
    public Integer artifact_id;
    public String ingredient_id;
    public Integer quantity;
    public String ingredient_image;  // Added field for ingredient image

    public IngredientWithQuantity(Integer recipe_id, Integer artifact_id,
                                  String ingredient_id, Integer quantity,
                                  String ingredient_image) {
        this.recipe_id = recipe_id;
        this.artifact_id = artifact_id;
        this.ingredient_id = ingredient_id;
        this.quantity = quantity;
        this.ingredient_image = ingredient_image;
    }

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public Integer getArtifact_id() {
        return artifact_id;
    }

    public void setArtifact_id(Integer artifact_id) {
        this.artifact_id = artifact_id;
    }

    public String getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(String ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getIngredient_image() {
        return ingredient_image;
    }

    public void setIngredient_image(String ingredient_image) {
        this.ingredient_image = ingredient_image;
    }
}
