package org.joe.LocalData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import org.joe.Data.Artifact;

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
}
