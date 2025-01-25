package org.joe.LocalData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.joe.Data.Artifact;
import org.joe.Data.Ingredient;
import org.joe.Data.Recipe;


@Database(entities = {Artifact.class, Recipe.class, Ingredient.class}, version = 1, exportSchema = false)
public abstract class ArtifactDatabase extends RoomDatabase {
    public abstract ArtifactDao artifactDao();

    private static volatile ArtifactDatabase instance;

    public static synchronized ArtifactDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ArtifactDatabase.class, "artifact_database")
                    .createFromAsset("database/artifact_database.db")
                    .build();
        }
        return instance;
    }
}