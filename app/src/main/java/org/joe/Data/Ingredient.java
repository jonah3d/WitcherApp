package org.joe.Data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "ingredient",
        indices = {
                @Index(value = {"name"}, unique = true)  // matches your schema's unique index
        })
public class Ingredient {
    @PrimaryKey
    @NonNull
    private String id;

    private String name;
    private String image;
    private String description;

    // Getters and setters
    @NonNull
    public String getId() { return id; }
    public void setId(@NonNull String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}