package com.miftakhularzak.moviecatalogue.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.miftakhularzak.moviecatalogue.data.source.local.room.FavoriteDao;

import java.util.List;

public class LocalRepository {
    private static LocalRepository INSTANCE;
    private FavoriteDao favoriteDao;

    public LocalRepository(FavoriteDao favoriteDao) {

        this.favoriteDao = favoriteDao;
    }

    public static LocalRepository getInstance(FavoriteDao favoriteDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(favoriteDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getFavoriteMovie() {
        return favoriteDao.getFavoriteMovie();
    }

    public DataSource.Factory<Integer, MovieEntity> getFavoriteMovieAsPaged() {
        return favoriteDao.getFavoriteMovieAsPaged();
    }

    public void insertFavoriteMovie(MovieEntity movieEntity) {
        favoriteDao.insertFavoriteMovie(movieEntity);
    }

    public void deleteFavoriteMovie(MovieEntity movieEntity) {
        favoriteDao.deleteFavoriteMovie(movieEntity);
    }

    public LiveData<List<TvShowEntity>> getFavoriteTvShow() {
        return favoriteDao.getFavoriteTvShow();
    }

    public DataSource.Factory<Integer, TvShowEntity> getFavoriteTvShowAsPaged() {
        return favoriteDao.getFavoriteTvShowAsPaged();
    }

    public void insertFavoriteTvShow(TvShowEntity tvShowEntity) {
        favoriteDao.insertFavoriteTvShow(tvShowEntity);
    }

    public void deleteFavoriteTvShow(TvShowEntity tvShowEntity) {
        favoriteDao.deleteFavoriteTvShow(tvShowEntity);
    }
}
