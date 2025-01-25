package org.joe.RemoteData;

import org.joe.Data.Section;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("sections.json")
    Call<SectionResponse> getSections();
    @GET("sections/{section}.json")
    Call<SectionResponse> getEntries(@Path("section") String section);


}

