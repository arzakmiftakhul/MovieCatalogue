package com.miftakhularzak.moviecatalogue.data.source.remote;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.miftakhularzak.moviecatalogue.api.ApiService;
import com.miftakhularzak.moviecatalogue.api.UtilsApi;
import com.miftakhularzak.moviecatalogue.BuildConfig;
import com.miftakhularzak.moviecatalogue.data.source.remote.response.MovieResponse;
import com.miftakhularzak.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.miftakhularzak.moviecatalogue.utils.Constants;
import com.miftakhularzak.moviecatalogue.utils.EspressoIdlingResource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private MovieResponse movie = null;
    private Application application;


    private RemoteRepository(Application application) {
        this.application = application;
    }

    public static RemoteRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(application);
        }
        return INSTANCE;
    }

    public void getMovies(GetMoviesCallback callback) {
        EspressoIdlingResource.increment();
        ApiService apiService = UtilsApi.getApiService();
        ArrayList<MovieResponse> listItems = new ArrayList<>();
        apiService.getNowPlayingMovie(Constants.MOVIE_API_KEY).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    JSONArray results = jsonObject.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject object = results.getJSONObject(i);
                        MovieResponse item = new MovieResponse(object);
//                        listItems.add(item);
                        listItems.add(item);
                    }
                    callback.onAllMoviesReceived(listItems);
                    EspressoIdlingResource.decrement();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void getMovieDetail(int movieId, GetDetailMovieCallback callback) {
        EspressoIdlingResource.increment();
        ApiService apiService = UtilsApi.getApiService();
        apiService.getMovieDetail(movieId, Constants.MOVIE_API_KEY).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    movie = new MovieResponse(jsonObject);
                    movie.setDetail(jsonObject);
                    callback.onMovieReceived(movie);
                    EspressoIdlingResource.decrement();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void getTvShows(GetTvShowCallback callback) {
        EspressoIdlingResource.increment();
        ApiService apiService = UtilsApi.getApiService();
        ArrayList<TvShowResponse> listItems = new ArrayList<>();
        apiService.getTvShow(BuildConfig.MOVIE_API_KEY).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    JSONArray results = jsonObject.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject object = results.getJSONObject(i);
                        TvShowResponse item = new TvShowResponse(object);
                        listItems.add(item);
                    }
                    callback.onAllTvShowReceived(listItems);
                    EspressoIdlingResource.decrement();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void getDetailTvShow(int tvId, GetDetailTvShowCallback callback) {
        EspressoIdlingResource.increment();
        ApiService apiService = UtilsApi.getApiService();
        apiService.getTvShowDetail(tvId, BuildConfig.MOVIE_API_KEY).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    TvShowResponse tvShowResponse = new TvShowResponse(jsonObject);
                    tvShowResponse.setDetail(jsonObject);
                    callback.onTvShowReceived(tvShowResponse);
                    EspressoIdlingResource.decrement();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public interface GetMoviesCallback {
        void onAllMoviesReceived(List<MovieResponse> movieResponses);

        void onDataNotAvailable();
    }

    public interface GetDetailMovieCallback {
        void onMovieReceived(MovieResponse movieResponse);

        void onDataNotAvailable();
    }

    public interface GetTvShowCallback {
        void onAllTvShowReceived(List<TvShowResponse> tvShowResponses);

        void onDataNotAvailable();
    }

    public interface GetDetailTvShowCallback {
        void onTvShowReceived(TvShowResponse tvShowResponse);

        void onDataNotAvailable();
    }
}
