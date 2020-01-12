package com.miftakhularzak.moviecatalogue.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.miftakhularzak.moviecatalogue.data.source.local.LocalRepository;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.miftakhularzak.moviecatalogue.data.source.remote.RemoteRepository;
import com.miftakhularzak.moviecatalogue.data.source.remote.response.MovieResponse;
import com.miftakhularzak.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.miftakhularzak.moviecatalogue.utils.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class FakeMovieCatalogueRepository implements MovieCatalogueDataSource {
    private volatile static FakeMovieCatalogueRepository INSTANCE = null;
    private final RemoteRepository remoteRepository;
    private final LocalRepository localRepository;
    private final AppExecutors appExecutors;

    FakeMovieCatalogueRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
        this.appExecutors = appExecutors;
    }

    public static FakeMovieCatalogueRepository getInstance(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (FakeMovieCatalogueRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FakeMovieCatalogueRepository(localRepository, remoteRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<MovieEntity>> getMovies() {
        MutableLiveData<List<MovieEntity>> listMovies = new MutableLiveData<>();
        remoteRepository.getMovies(new RemoteRepository.GetMoviesCallback() {
            @Override
            public void onAllMoviesReceived(List<MovieResponse> movieResponses) {
                ArrayList<MovieEntity> listItem = new ArrayList<>();
                for (int i = 0; i < movieResponses.size(); i++) {
                    MovieResponse response = movieResponses.get(i);
                    MovieEntity movie = new MovieEntity(response.getId(),
                            response.getTitle(),
                            response.getOverview(),
                            response.getReleaseDate(),
                            response.getPosterUrl());
                    listItem.add(movie);
                }
                listMovies.postValue(listItem);
            }

            @Override
            public void onDataNotAvailable() {
            }
        });

        return listMovies;
    }

    @Override
    public LiveData<MovieEntity> getDetailMovie(int movieId) {
        MutableLiveData<MovieEntity> movieDetail = new MutableLiveData<>();
        remoteRepository.getMovieDetail(movieId, new RemoteRepository.GetDetailMovieCallback() {
            @Override
            public void onMovieReceived(MovieResponse movieResponse) {
                MovieEntity movie = new MovieEntity(movieResponse.getId(),
                        movieResponse.getTitle(),
                        movieResponse.getOverview(),
                        movieResponse.getReleaseDate(),
                        movieResponse.getPosterUrl(),
                        movieResponse.getGenre(),
                        movieResponse.getCompanies(),
                        movieResponse.getLanguage(),
                        movieResponse.getRating(),
                        movieResponse.getRuntime());
                movieDetail.postValue(movie);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return movieDetail;
    }

    @Override
    public LiveData<List<TvShowEntity>> getTvShow() {
        MutableLiveData<List<TvShowEntity>> listTvShow = new MutableLiveData<>();
        remoteRepository.getTvShows(new RemoteRepository.GetTvShowCallback() {
            @Override
            public void onAllTvShowReceived(List<TvShowResponse> tvShowResponses) {
                ArrayList<TvShowEntity> listItem = new ArrayList<>();
                for (int i = 0; i < tvShowResponses.size(); i++) {
                    TvShowResponse response = tvShowResponses.get(i);
                    TvShowEntity movie = new TvShowEntity(response.getId(),
                            response.getName(),
                            response.getOverview(),
                            response.getPosterUrl());
                    listItem.add(movie);
                }
                listTvShow.postValue(listItem);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return listTvShow;
    }

    @Override
    public LiveData<TvShowEntity> getDetailTvShow(int tvId) {
        MutableLiveData<TvShowEntity> tvShowDetail = new MutableLiveData<>();
        remoteRepository.getDetailTvShow(tvId, new RemoteRepository.GetDetailTvShowCallback() {
            @Override
            public void onTvShowReceived(TvShowResponse tvShowResponse) {
                TvShowEntity tv = new TvShowEntity(tvShowResponse.getId(),
                        tvShowResponse.getName(),
                        tvShowResponse.getOverview(),
                        tvShowResponse.getPosterUrl(),
                        tvShowResponse.getGenre(),
                        tvShowResponse.getCompanies(),
                        tvShowResponse.getLanguage(),
                        tvShowResponse.getRating(),
                        tvShowResponse.getDate());
                tvShowDetail.postValue(tv);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return tvShowDetail;
    }

    @Override
    public LiveData<List<MovieEntity>> getFavoriteMovies() {
        return localRepository.getFavoriteMovie();
    }

    @Override
    public LiveData<PagedList<MovieEntity>> getFavoriteMoviesAsPaged() {
        return new LivePagedListBuilder<>(localRepository.getFavoriteMovieAsPaged(), 5).build();
    }

    @Override
    public void insertFavoriteMovie(MovieEntity movieEntity) {
        Runnable runnable = () -> localRepository.insertFavoriteMovie(movieEntity);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteFavoriteMovie(MovieEntity movieEntity) {
        Runnable runnable = () -> localRepository.deleteFavoriteMovie(movieEntity);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public LiveData<List<TvShowEntity>> getFavoriteTvShow() {
        return localRepository.getFavoriteTvShow();
    }

    @Override
    public LiveData<PagedList<TvShowEntity>> getFavoriteTvShowAsPaged() {
        return new LivePagedListBuilder<>(localRepository.getFavoriteTvShowAsPaged(), 5).build();
    }

    @Override
    public void insertFavoriteTvShow(TvShowEntity tvShowEntity) {
        Runnable runnable = () -> localRepository.insertFavoriteTvShow(tvShowEntity);
        appExecutors.diskIO().execute(runnable);
    }


    @Override
    public void deleteFavoriteTvShow(TvShowEntity tvShowEntity) {
        Runnable runnable = () -> localRepository.deleteFavoriteTvShow(tvShowEntity);
        appExecutors.diskIO().execute(runnable);
    }
}
