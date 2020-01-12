package com.miftakhularzak.moviecatalogue.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.miftakhularzak.moviecatalogue.data.source.MovieCatalogueRepository;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;

import java.util.List;

public class TvShowViewModel extends ViewModel {
    private MovieCatalogueRepository movieCatalogueRepository;

    public TvShowViewModel(){}

    public TvShowViewModel(MovieCatalogueRepository movieCatalogueRepository) {
        this.movieCatalogueRepository = movieCatalogueRepository;
    }

    public LiveData<List<TvShowEntity>> getTvShow() {
        return movieCatalogueRepository.getTvShow();
    }

    public LiveData<TvShowEntity> getTvDetail(int tvId) {
        return movieCatalogueRepository.getDetailTvShow(tvId);
    }
    public LiveData<List<TvShowEntity>> getFavoriteTvShow(){
        return movieCatalogueRepository.getFavoriteTvShow();
    }

    public LiveData<PagedList<TvShowEntity>> getFavoriteTvShowAsPaged(){
        return movieCatalogueRepository.getFavoriteTvShowAsPaged();
    }

    public void insertFavoriteTvShow(TvShowEntity tvShowEntity){
        movieCatalogueRepository.insertFavoriteTvShow(tvShowEntity);
    }

    public void deleteFaoriteTvShow(TvShowEntity tvShowEntity){
        movieCatalogueRepository.deleteFavoriteTvShow(tvShowEntity);
    }
}
