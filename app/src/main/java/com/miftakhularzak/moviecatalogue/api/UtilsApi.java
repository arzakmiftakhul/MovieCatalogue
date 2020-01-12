package com.miftakhularzak.moviecatalogue.api;

public class UtilsApi {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static ApiService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
