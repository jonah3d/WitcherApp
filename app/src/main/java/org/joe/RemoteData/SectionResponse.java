package org.joe.RemoteData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joe.Data.Entry;
import org.joe.Data.Section;

import java.util.List;

public class SectionResponse {
    @SerializedName("sections")
    @Expose
    private List<Section> sections;
    private List<Entry> entries;

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    private Entry getEntry(int position) {
        return entries.get(position);
    }
}
