package org.joe.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Section {

    @SerializedName("title")
    @Expose
    private  String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("entries")
    @Expose
    private ArrayList<Entry> entries;

    public Section(){

    }
    public Section(String title, String subtitle, String image) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;

        entries = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "Section{ \n"  +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", image='" + image + '\'' +
                ", entries= \n" + entries +
                '}';
    }
}
