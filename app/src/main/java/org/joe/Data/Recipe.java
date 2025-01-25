package org.joe.Data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(
        tableName = "recipe",
        foreignKeys = {
                @ForeignKey(
                        entity = Artifact.class,
                        parentColumns = "id",
                        childColumns = "artifact",
                        onDelete = ForeignKey.NO_ACTION,
                        onUpdate = ForeignKey.NO_ACTION
                ),
                @ForeignKey(
                        entity = Ingredient.class,
                        parentColumns = "id",
                        childColumns = "ingredient",
                        onDelete = ForeignKey.NO_ACTION,
                        onUpdate = ForeignKey.NO_ACTION
                )
        }
)
public class Recipe {
    @PrimaryKey
    @NonNull
    private Integer id;

    private Integer artifact;
    private String ingredient;
    private Integer quantity;

    // Getters and setters
    @NonNull
    public Integer getId() { return id; }
    public void setId(@NonNull Integer id) { this.id = id; }
    public Integer getArtifact() { return artifact; }
    public void setArtifact(Integer artifact) { this.artifact = artifact; }
    public String getIngredient() { return ingredient; }
    public void setIngredient(String ingredient) { this.ingredient = ingredient; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
