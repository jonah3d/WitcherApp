package org.joe.RemoteData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joe.Data.Entry;

import java.util.List;

public class EntryResponse {

    @SerializedName("entries")
    @Expose
        private List<Entry> entries;

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
