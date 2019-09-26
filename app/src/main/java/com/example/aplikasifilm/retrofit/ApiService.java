package com.example.aplikasifilm.retrofit;

import com.example.aplikasifilm.model.ResponseFilm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<ResponseFilm> ambilDataFilm(
            @Query("api_key") String apikey
    );
}
