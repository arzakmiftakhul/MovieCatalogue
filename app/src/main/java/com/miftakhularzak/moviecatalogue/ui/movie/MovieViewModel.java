package com.miftakhularzak.moviecatalogue.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.miftakhularzak.moviecatalogue.data.source.MovieCatalogueRepository;
import com.miftakhularzak.moviecatalogue.data.source.local.LocalRepository;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private final String TAG = "MOVIE_VIEW_MODEL";
    private MovieCatalogueRepository movieCatalogueRepository;
    public MovieViewModel(){}
    public MovieViewModel(MovieCatalogueRepository movieCatalogueRepository) {
        this.movieCatalogueRepository = movieCatalogueRepository;
    }

    public LiveData<List<MovieEntity>> getMovies() {
       return movieCatalogueRepository.getMovies();
    }

    public LiveData<MovieEntity> getMovieDetail(int movieId) {
        return movieCatalogueRepository.getDetailMovie(movieId);
    }
    public LiveData<PagedList<MovieEntity>> getFavoriteMoviesAsPaged(){
        return movieCatalogueRepository.getFavoriteMoviesAsPaged();
    }
    public LiveData<List<MovieEntity>> getFavoriteMovies(){
        return movieCatalogueRepository.getFavoriteMovies();
    }
    public void insertFavoriteMovie(MovieEntity movieEntity){
        movieCatalogueRepository.insertFavoriteMovie(movieEntity);
    }
    public void deleteFavoriteMovie(MovieEntity movieEntity){
        movieCatalogueRepository.deleteFavoriteMovie(movieEntity);
    }
}
