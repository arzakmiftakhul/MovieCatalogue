package com.miftakhularzak.moviecatalogue.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.miftakhularzak.moviecatalogue.data.source.MovieCatalogueRepository;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.miftakhularzak.moviecatalogue.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowViewModelTest {
    private TvShowViewModel tvShowViewModel;
    private TvShowEntity dummyTvShow;
    private MovieCatalogueRepository movieCatalogueRepository = mock(MovieCatalogueRepository.class);
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Mock
    PagedList<TvShowEntity> pagedList;
    @Mock
    Observer<List<TvShowEntity>> listTvShowObserver;
    @Mock
    Observer<TvShowEntity> observer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tvShowViewModel = new TvShowViewModel(movieCatalogueRepository);
        dummyTvShow = new TvShowEntity(60625,
                "Rick and Morty",
                "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
                "/qJdfO3ahgAMf2rcmhoqngjBBZW1.jpg",
                "Animation, Comedy, Sci-Fi & Fantasy",
                "William Street, Harmonius Claptrap",
                "English",
                "8.6",
                "2019-12-08");
    }

    @Test
    public void getTvShow() {
        List<TvShowEntity> listTvShow = DataDummy.generateTvShowDummy();
        MutableLiveData<List<TvShowEntity>> tvShows = new MutableLiveData<>();
        tvShows.postValue(listTvShow);
        when(movieCatalogueRepository.getTvShow()).thenReturn(tvShows);
        tvShowViewModel.getTvShow().observeForever(listTvShowObserver);
        verify(listTvShowObserver).onChanged(listTvShow);
    }

    @Test
    public void getTvDetail() {
        MutableLiveData<TvShowEntity> tvShow = new MutableLiveData<>();
        tvShow.postValue(dummyTvShow);
        when(movieCatalogueRepository.getDetailTvShow(dummyTvShow.getTvId())).thenReturn(tvShow);
        tvShowViewModel.getTvDetail(dummyTvShow.getTvId()).observeForever(observer);
        verify(observer).onChanged(dummyTvShow);
    }

    @Test
    public void getFavoriteTvShow() {
        List<TvShowEntity> listTvShow = DataDummy.generateTvShowDummy();
        MutableLiveData<List<TvShowEntity>> tvShows = new MutableLiveData<>();
        tvShows.postValue(listTvShow);
        when(movieCatalogueRepository.getFavoriteTvShow()).thenReturn(tvShows);
        tvShowViewModel.getFavoriteTvShow().observeForever(listTvShowObserver);
        verify(listTvShowObserver).onChanged(listTvShow);
        verify(movieCatalogueRepository).getFavoriteTvShow();
        assertEquals(DataDummy.generateTvShowDummy().size(), listTvShow.size());
    }

    @Test
    public void getFavoriteTvShowAsPaged() {
        MutableLiveData<PagedList<TvShowEntity>> tvShows = new MutableLiveData<>();
        tvShows.postValue(pagedList);
        when(movieCatalogueRepository.getFavoriteTvShowAsPaged()).thenReturn(tvShows);
        tvShowViewModel.getFavoriteTvShowAsPaged().observeForever(listTvShowObserver);
        verify(listTvShowObserver).onChanged(pagedList);
        verify(movieCatalogueRepository).getFavoriteTvShowAsPaged();
    }

    @Test
    public void insertFavoriteMovieTest() {
        tvShowViewModel.insertFavoriteTvShow(dummyTvShow);
        verify(movieCatalogueRepository).insertFavoriteTvShow(dummyTvShow);
    }

    @Test
    public void deleteFavoriteMovieTest() {
        tvShowViewModel.deleteFaoriteTvShow(dummyTvShow);
        verify(movieCatalogueRepository).deleteFavoriteTvShow(dummyTvShow);
    }
}