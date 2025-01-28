package org.joe;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.joe.Data.Artifact;
import org.joe.Data.Ingredient;
import org.joe.Data.IngredientWithQuantity;
import org.joe.Data.Recipe;
import org.joe.LocalData.ArtifactDatabase;
import org.joe.dataRepository.ArtifactRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlchemyViewModel extends AndroidViewModel {

    private ArtifactRepository artifactRepository;
    private LiveData<List<Artifact>> artifacts;
    public AlchemyViewModel(@NonNull Application application) {
        super(application);

       var artifactDao = ArtifactDatabase.getInstance(application).artifactDao();
       artifactRepository = new ArtifactRepository(artifactDao);
       artifacts = artifactRepository.getAllArtifacts();
    }

    public LiveData<List<Artifact>> getArtifacts() {
        return artifacts;
    }

    public LiveData<List<Ingredient>> getAllIngredients() {
        return artifactRepository.getAllIngredients();
    }
    public LiveData<List<IngredientWithQuantity>> getRecipe(Integer artifactId) {
        return artifactRepository.getRecipe(artifactId);
    }
    public void updateArtifact(Artifact artifact) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> artifactRepository.updateArtifact(artifact));
    }

    public LiveData<List<Artifact>> getBombArtifacts() {
        return artifactRepository.getBombArtifacts();
    }

    public LiveData<List<Artifact>> getOilArtifacts() {
        return artifactRepository.getOilArtifacts();
    }

    public LiveData<List<Artifact>> getPotionArtifacts() {
        return artifactRepository.getPotionArtifacts();
    }

    public LiveData<List<Artifact>> getSignArtifacts() {
        return artifactRepository.getSignArtifacts();
    }
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    public void addOrUpdateIngredientInRecipe(Recipe recipe) {
        executor.execute(() -> {
            Recipe existingRecipe = artifactRepository.getRecipeByArtifactAndIngredient(
                    recipe.getArtifact(),
                    recipe.getIngredient()
            );

            if (existingRecipe != null) {
                // Use the existing recipe's ID and update its quantity
                existingRecipe.setQuantity(existingRecipe.getQuantity() + recipe.getQuantity());
                artifactRepository.updateRecipe(existingRecipe);  // Use update instead of insert
            } else {
                artifactRepository.addIngredientToRecipe(recipe);
            }
        });
    }
    public void removeIngredientFromRecipe(Recipe recipe) {
        executor.execute(() -> {
            Recipe existingRecipe = artifactRepository.getRecipeByArtifactAndIngredient(
                    recipe.getArtifact(),
                    recipe.getIngredient()
            );

            if (existingRecipe != null) {
                int newQuantity = existingRecipe.getQuantity() - 1;
                if (newQuantity <= 0) {
                    // Delete the recipe if quantity reaches 0
                    artifactRepository.deleteRecipe(existingRecipe);
                } else {
                    // Update with reduced quantity
                    existingRecipe.setQuantity(newQuantity);
                    artifactRepository.updateRecipe(existingRecipe);
                }
            }
        });
    }



//    public void addIngredientToRecipe(Recipe ingredientWithQuantity) {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.execute(() -> artifactRepository.addIngredientToRecipe(ingredientWithQuantity));
//    }
}
