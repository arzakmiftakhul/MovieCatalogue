package com.miftakhularzak.moviecatalogue.di;
import android.app.Application;

import com.miftakhularzak.moviecatalogue.data.source.MovieCatalogueRepository;
import com.miftakhularzak.moviecatalogue.data.source.local.LocalRepository;
import com.miftakhularzak.moviecatalogue.data.source.local.room.FavoriteDatabase;
import com.miftakhularzak.moviecatalogue.data.source.remote.RemoteRepository;
import com.miftakhularzak.moviecatalogue.utils.AppExecutors;

public class Injection {
    public static MovieCatalogueRepository provideRepository(Application application) {
        FavoriteDatabase favoriteDatabase = FavoriteDatabase.getInstance(application);
        RemoteRepository remoteRepository = RemoteRepository.getInstance(application);
        LocalRepository localRepository = LocalRepository.getInstance(favoriteDatabase.favoriteDao());
        AppExecutors appExecutors = new AppExecutors();
        return MovieCatalogueRepository.getInstance(localRepository,remoteRepository,appExecutors);
    }
}
