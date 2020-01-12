package com.miftakhularzak.moviecatalogue.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/now_playing")
    Call<JsonObject> getNowPlayingMovie(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<JsonObject> getMovieDetail(@Path("movie_id")int movieId,
                                    @Query("api_key")String apiKey);
    @GET("tv/popular")
    Call<JsonObject> getTvShow(@Query("api_key")String apiKey);

    @GET("tv/{tv_id}")
    Call<JsonObject> getTvShowDetail(@Path("tv_id")int tvId,
                                     @Query("api_key")String apiKey);
}
