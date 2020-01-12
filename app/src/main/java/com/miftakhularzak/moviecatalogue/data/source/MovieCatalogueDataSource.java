package com.miftakhularzak.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;

import java.util.List;

public interface MovieCatalogueDataSource {
    LiveData<List<MovieEntity>> getMovies();
    LiveData<MovieEntity> getDetailMovie(int movieId);
    LiveData<List<TvShowEntity>>getTvShow();
    LiveData<TvShowEntity> getDetailTvShow(int tvId);

    LiveData<List<MovieEntity>>getFavoriteMovies();
    LiveData<PagedList<MovieEntity>>getFavoriteMoviesAsPaged();
    void insertFavoriteMovie(MovieEntity movieEntity);
    void deleteFavoriteMovie(MovieEntity movieEntity);

    LiveData<List<TvShowEntity>>getFavoriteTvShow();
    LiveData<PagedList<TvShowEntity>>getFavoriteTvShowAsPaged();
    void insertFavoriteTvShow(TvShowEntity tvShowEntity);
    void deleteFavoriteTvShow(TvShowEntity tvShowEntity);
}
