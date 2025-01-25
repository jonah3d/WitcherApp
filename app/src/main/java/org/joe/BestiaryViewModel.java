package org.joe;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.joe.Data.Entry;
import org.joe.Data.Section;
import org.joe.RemoteData.ApiClient;
import org.joe.RemoteData.ApiService;
import org.joe.RemoteData.EntryResponse;
import org.joe.RemoteData.SectionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class BestiaryViewModel extends AndroidViewModel {
    private static final String TAG = "BestiaryViewModel";
    private MutableLiveData<List<Section>> sections = new MutableLiveData<>();
    private ApiService service;
    private MutableLiveData<List<Entry>> entries = new MutableLiveData<>();
    private String currentSection; // Add this to track current section

    public MutableLiveData<List<Entry>> getEntries() {
        return entries;
    }

    public MutableLiveData<List<Section>> getSections() {
        if (sections.getValue() == null) {
            downloadSections(); // Retry if sections are null
        }
        return sections;
    }

    public BestiaryViewModel(@NonNull Application application) {
        super(application);
        service = ApiClient.getRetrofit().create(ApiService.class);
        downloadSections();
    }

    private void downloadSections() {
        service.getSections().enqueue(new Callback<SectionResponse>() {
            @Override
            public void onResponse(Call<SectionResponse> call, Response<SectionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    sections.setValue(response.body().getSections());
                    // If we have a current section, reload its entries
                    if (currentSection != null) {
                        LoadEntries(currentSection);
                    }
                } else {
                    Log.e(TAG, "Failed to fetch sections: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<SectionResponse> call, Throwable t) {
                Log.e(TAG, "Error fetching sections: ", t);
            }
        });
    }

    public void LoadEntries(String sectionFileName) {
        currentSection = sectionFileName; // Store current section
        String formattedName = sectionFileName.replace(" ", "_").toLowerCase();

        service.getEntries(formattedName).enqueue(new Callback<SectionResponse>() {
            @Override
            public void onResponse(Call<SectionResponse> call, Response<SectionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    entries.setValue(response.body().getEntries());
                    Log.d(TAG, "onResponse: " + response.body().getEntries());
                } else {
                    Log.e(TAG, "Failed to fetch entries: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<SectionResponse> call, Throwable t) {
                Log.e(TAG, "Error fetching entries: ", t);
            }
        });
    }
}