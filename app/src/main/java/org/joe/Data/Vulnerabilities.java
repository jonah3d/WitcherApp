
package org.joe.Data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Vulnerabilities {

    @SerializedName("bombs")
    @Expose
    private List<String> bombs;
    @SerializedName("oils")
    @Expose
    private List<String> oils;
    @SerializedName("potions")
    @Expose
    private List<Object> potions;
    @SerializedName("signs")
    @Expose
    private List<String> signs;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vulnerabilities() {
    }

    public Vulnerabilities(List<String> bombs, List<String> oils, List<Object> potions, List<String> signs) {
        super();
        this.bombs = bombs;
        this.oils = oils;
        this.potions = potions;
        this.signs = signs;
    }

    public List<String> getBombs() {
        return bombs;
    }

    public void setBombs(List<String> bombs) {
        this.bombs = bombs;
    }

    public List<String> getOils() {
        return oils;
    }

    public void setOils(List<String> oils) {
        this.oils = oils;
    }

    public List<Object> getPotions() {
        return potions;
    }

    public void setPotions(List<Object> potions) {
        this.potions = potions;
    }

    public List<String> getSigns() {
        return signs;
    }

    public void setSigns(List<String> signs) {
        this.signs = signs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Vulnerabilities.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bombs");
        sb.append('=');
        sb.append(((this.bombs == null)?"<null>":this.bombs));
        sb.append(',');
        sb.append("oils");
        sb.append('=');
        sb.append(((this.oils == null)?"<null>":this.oils));
        sb.append(',');
        sb.append("potions");
        sb.append('=');
        sb.append(((this.potions == null)?"<null>":this.potions));
        sb.append(',');
        sb.append("signs");
        sb.append('=');
        sb.append(((this.signs == null)?"<null>":this.signs));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
