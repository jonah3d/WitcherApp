
package org.joe.Data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Entry {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("expansion")
    @Expose
    private String expansion;
    @SerializedName("intro")
    @Expose
    private String intro;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("vulnerabilities")
    @Expose
    private Vulnerabilities vulnerabilities;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Entry() {
    }

    public Entry(String title, String expansion, String intro, String author, String detail, String image, Vulnerabilities vulnerabilities) {
        super();
        this.title = title;
        this.expansion = expansion;
        this.intro = intro;
        this.author = author;
        this.detail = detail;
        this.image = image;
        this.vulnerabilities = vulnerabilities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Vulnerabilities getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(Vulnerabilities vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Entry.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("expansion");
        sb.append('=');
        sb.append(((this.expansion == null)?"<null>":this.expansion));
        sb.append(',');
        sb.append("intro");
        sb.append('=');
        sb.append(((this.intro == null)?"<null>":this.intro));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null)?"<null>":this.author));
        sb.append(',');
        sb.append("detail");
        sb.append('=');
        sb.append(((this.detail == null)?"<null>":this.detail));
        sb.append(',');
        sb.append("image");
        sb.append('=');
        sb.append(((this.image == null)?"<null>":this.image));
        sb.append(',');
        sb.append("vulnerabilities");
        sb.append('=');
        sb.append(((this.vulnerabilities == null)?"<null>":this.vulnerabilities));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
