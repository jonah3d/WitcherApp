package org.joe.dataRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.joe.Data.Artifact;
import org.joe.Data.Ingredient;
import org.joe.Data.IngredientWithQuantity;
import org.joe.Data.Recipe;
import org.joe.LocalData.ArtifactDao;

import java.util.List;

public class ArtifactRepository {
    private ArtifactDao artifactDao;

    public ArtifactRepository(ArtifactDao artifactDao) {
        this.artifactDao = artifactDao;
    }

    public LiveData<List<Artifact>> getAllArtifacts() {
        return artifactDao.getAllArtifacts();
    }

    public LiveData<List<Artifact>> getBombArtifacts() {
        return artifactDao.getBombArtifacts();
    }

    public LiveData<List<Artifact>> getOilArtifacts() {
        return artifactDao.getOilArtifacts();
    }

    public LiveData<List<Artifact>> getPotionArtifacts() {
        return artifactDao.getPotionArtifacts();
    }

    public LiveData<List<Artifact>> getSignArtifacts() {
        return artifactDao.getSignArtifacts();
    }

    public void updateArtifact(Artifact artifact) {
        artifactDao.updateArtifact(artifact);
    }

    public LiveData<List<Ingredient>> getAllIngredients() {
        return artifactDao.getAllIngredients();
    }
    public LiveData<List<IngredientWithQuantity>> getRecipe(Integer artifactId) {
        return artifactDao.getRecipe(artifactId);
    }

    public void addIngredientToRecipe(Recipe recipe) {
        artifactDao.addIngredientToRecipe(recipe);
    }
    public void updateRecipe(Recipe recipe) {
        artifactDao.updateRecipe(recipe);
    }
    public void deleteRecipe(Recipe recipe) {
        artifactDao.deleteRecipe(recipe);
    }

    public Recipe getRecipeByArtifactAndIngredient(Integer artifactId, String ingredientId) {
        return artifactDao.getRecipeByArtifactAndIngredient(artifactId, ingredientId);
    }
}
