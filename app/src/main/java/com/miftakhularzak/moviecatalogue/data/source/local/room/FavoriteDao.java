package com.miftakhularzak.moviecatalogue.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;

import java.util.List;

import androidx.paging.DataSource;

@Dao
public interface FavoriteDao {
    @WorkerThread
    @Query("SELECT * FROM movieentities")
    LiveData<List<MovieEntity>> getFavoriteMovie();

    @WorkerThread
    @Query("SELECT * FROM movieentities")
    DataSource.Factory<Integer,MovieEntity>getFavoriteMovieAsPaged();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteMovie(MovieEntity movieEntity);

    @Delete
    void deleteFavoriteMovie(MovieEntity movieEntity);

    @WorkerThread
    @Query("SELECT * FROM tvshowentities")
    LiveData<List<TvShowEntity>> getFavoriteTvShow();

    @WorkerThread
    @Query("SELECT * FROM tvshowentities")
    DataSource.Factory<Integer,TvShowEntity>getFavoriteTvShowAsPaged();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteTvShow(TvShowEntity tvShowEntity);

    @Delete
    void deleteFavoriteTvShow(TvShowEntity TvShow);

}
