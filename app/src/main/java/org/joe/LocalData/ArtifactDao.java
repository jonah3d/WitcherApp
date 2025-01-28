package org.joe.LocalData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.joe.Data.Artifact;
import org.joe.Data.Ingredient;
import org.joe.Data.IngredientWithQuantity;
import org.joe.Data.Recipe;

import java.util.List;

@Dao
public interface ArtifactDao {

    @Query("SELECT * FROM artifact")
    public LiveData<List<Artifact>> getAllArtifacts();

    @Query("SELECT * FROM artifact WHERE id_type = 1")
    public LiveData<List<Artifact>> getBombArtifacts();
    @Query("SELECT * FROM artifact WHERE id_type = 2")
    public LiveData<List<Artifact>> getOilArtifacts();
    @Query("SELECT * FROM artifact WHERE id_type = 3")
    public LiveData<List<Artifact>> getPotionArtifacts();
    @Query("SELECT * FROM artifact WHERE id_type = 4")
    public LiveData<List<Artifact>> getSignArtifacts();

    @Update
    public void updateArtifact(Artifact artifact);

    @Query("SELECT * FROM INGREDIENT")
    public LiveData<List<Ingredient>> getAllIngredients();

    @Query("SELECT r.id as recipe_id, " +
            "r.artifact as artifact_id, " +
            "r.ingredient as ingredient_id, " +
            "r.quantity as quantity, " +
            "i.image as ingredient_image " +
            "FROM recipe r " +
            "INNER JOIN ingredient i ON r.ingredient = i.id " +
            "WHERE r.artifact = :artifactId")
    public LiveData<List<IngredientWithQuantity>> getRecipe(Integer artifactId);

    @Query("SELECT * FROM recipe WHERE artifact = :artifactId AND ingredient = :ingredientId LIMIT 1")
    Recipe getRecipeByArtifactAndIngredient(Integer artifactId, String ingredientId);

    @Delete
    void deleteRecipe(Recipe recipe);
    @Update
    void updateRecipe(Recipe recipe);
    @Insert
    public void addIngredientToRecipe(Recipe recipe);
}
