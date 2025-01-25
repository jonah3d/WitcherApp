package org.joe.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "artifact",
        indices = {
                @Index(value = {"key"}, unique = true)  // matches your schema's unique index
        })
public class Artifact implements Serializable {
    @PrimaryKey
    @NonNull
    private Integer id;

    private String key;
    private String name;
    private String effect;

    @NonNull
    @ColumnInfo(name = "id_type")
    private Integer idType;

    private String image;
    private Integer charges;
    private Integer duration;

    @ColumnInfo(name = "fire_damage")
    private Integer fireDamage;

    @ColumnInfo(name = "silver_damage")
    private Integer silverDamage;

    @ColumnInfo(name = "phy_damage")
    private Integer phyDamage;

    // Getters and setters
    @NonNull
    public Integer getId() { return id; }
    public void setId(@NonNull Integer id) { this.id = id; }
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEffect() { return effect; }
    public void setEffect(String effect) { this.effect = effect; }
    @NonNull
    public Integer getIdType() { return idType; }
    public void setIdType(@NonNull Integer idType) { this.idType = idType; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public Integer getCharges() { return charges; }
    public void setCharges(Integer charges) { this.charges = charges; }
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    public Integer getFireDamage() { return fireDamage; }
    public void setFireDamage(Integer fireDamage) { this.fireDamage = fireDamage; }
    public Integer getSilverDamage() { return silverDamage; }
    public void setSilverDamage(Integer silverDamage) { this.silverDamage = silverDamage; }
    public Integer getPhyDamage() { return phyDamage; }
    public void setPhyDamage(Integer phyDamage) { this.phyDamage = phyDamage; }
}