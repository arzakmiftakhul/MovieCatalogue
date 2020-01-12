package com.miftakhularzak.moviecatalogue.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.miftakhularzak.moviecatalogue.data.source.MovieCatalogueRepository;
import com.miftakhularzak.moviecatalogue.di.Injection;
import com.miftakhularzak.moviecatalogue.ui.movie.MovieViewModel;
import com.miftakhularzak.moviecatalogue.ui.tvshow.TvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final MovieCatalogueRepository mMovieCatalogueRepository;

    private ViewModelFactory(MovieCatalogueRepository movieRepository) {
        mMovieCatalogueRepository = movieRepository;
    }


    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(mMovieCatalogueRepository);
        }else if(modelClass.isAssignableFrom(TvShowViewModel.class)){
            return (T) new TvShowViewModel(mMovieCatalogueRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
