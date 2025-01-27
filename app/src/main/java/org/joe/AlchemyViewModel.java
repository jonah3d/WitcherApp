package org.joe;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.joe.Data.Artifact;
import org.joe.Data.Ingredient;
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
}
