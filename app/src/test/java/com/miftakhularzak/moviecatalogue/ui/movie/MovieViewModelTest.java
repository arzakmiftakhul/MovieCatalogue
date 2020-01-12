package com.miftakhularzak.moviecatalogue.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.miftakhularzak.moviecatalogue.data.source.MovieCatalogueRepository;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
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


public class MovieViewModelTest {
    private MovieViewModel movieViewModel;
    private MovieEntity dummyMovie;
    private MovieCatalogueRepository movieCatalogueRepository = mock(MovieCatalogueRepository.class);
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Mock
    PagedList<MovieEntity> pagedList;
    @Mock
    Observer<List<MovieEntity>> listMovieObserver;
    @Mock
    Observer<MovieEntity> observer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movieViewModel = new MovieViewModel(movieCatalogueRepository);
        dummyMovie = new MovieEntity(330457,
                "Frozen II",
                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "2019-11-20",
                "/pjeMs3yqRmFL3giJy4PMXWZTTPa.jpg",
                "Animation, Family, Music",
                "Walt Disney Animation Studios, Walt Disney Pictures",
                "English",
                "7",
                "104");
    }

    @Test
    public void getMovies() {
        List<MovieEntity> listMovie = DataDummy.generateMovieDummy();
        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
        movies.postValue(listMovie);
        when(movieCatalogueRepository.getMovies()).thenReturn(movies);
        movieViewModel.getMovies().observeForever(listMovieObserver);
        verify(listMovieObserver).onChanged(listMovie);
        verify(movieCatalogueRepository).getMovies();
        assertEquals(DataDummy.generateMovieDummy().size(), listMovie.size());
    }

    @Test
    public void getMovieDetail() {
        MutableLiveData<MovieEntity> movie = new MutableLiveData<>();
        movie.postValue(dummyMovie);
        when(movieCatalogueRepository.getDetailMovie(dummyMovie.getMovieId())).thenReturn(movie);
        movieViewModel.getMovieDetail(dummyMovie.getMovieId()).observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }

    @Test
    public void getFavoriteMovie() {
        List<MovieEntity> listMovie = DataDummy.generateMovieDummy();
        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
        movies.postValue(listMovie);
        when(movieCatalogueRepository.getFavoriteMovies()).thenReturn(movies);
        movieViewModel.getFavoriteMovies().observeForever(listMovieObserver);
        verify(listMovieObserver).onChanged(listMovie);
        verify(movieCatalogueRepository).getFavoriteMovies();
        assertEquals(DataDummy.generateMovieDummy().size(), listMovie.size());
    }

    @Test
    public void getFavoriteMovieAsPaged() {
        MutableLiveData<PagedList<MovieEntity>> movies = new MutableLiveData<>();
        movies.postValue(pagedList);
        when(movieCatalogueRepository.getFavoriteMoviesAsPaged()).thenReturn(movies);
        movieViewModel.getFavoriteMoviesAsPaged().observeForever(listMovieObserver);
        verify(listMovieObserver).onChanged(pagedList);
        verify(movieCatalogueRepository).getFavoriteMoviesAsPaged();
    }

    @Test
    public void insertFavoriteMovieTest() {
        movieViewModel.insertFavoriteMovie(dummyMovie);
        verify(movieCatalogueRepository).insertFavoriteMovie(dummyMovie);
    }

    @Test
    public void deleteFavoriteMovieTest() {
        movieViewModel.deleteFavoriteMovie(dummyMovie);
        verify(movieCatalogueRepository).deleteFavoriteMovie(dummyMovie);
    }

}