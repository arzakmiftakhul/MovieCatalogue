package com.miftakhularzak.moviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.miftakhularzak.moviecatalogue.data.source.local.LocalRepository;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.miftakhularzak.moviecatalogue.data.source.remote.RemoteRepository;
import com.miftakhularzak.moviecatalogue.data.source.remote.response.MovieResponse;
import com.miftakhularzak.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.miftakhularzak.moviecatalogue.utils.DataDummy;
import com.miftakhularzak.moviecatalogue.utils.InstantAppExecutors;
import com.miftakhularzak.moviecatalogue.utils.LiveDataTestUtil;
import com.miftakhularzak.moviecatalogue.utils.PagedListUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieCatalogueRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock
    private DataSource.Factory<Integer, MovieEntity> movieDataSourceFactory;
    @Mock
    private DataSource.Factory<Integer, TvShowEntity> tvShowDataSourceFactory;

    private RemoteRepository remote = mock(RemoteRepository.class);
    private LocalRepository local = mock(LocalRepository.class);
    private InstantAppExecutors appExecutors = mock(InstantAppExecutors.class);

    private FakeMovieCatalogueRepository fakeMovieCatalogueRepository = new FakeMovieCatalogueRepository(local, remote, appExecutors);
    private ArrayList<MovieResponse> movieResponses = DataDummy.generateMovieResponseDummy();
    private int movieId = movieResponses.get(0).getId();
    private ArrayList<TvShowResponse> tvShowResponses = DataDummy.generateTvShowResponseDummy();
    private int tvShowId = tvShowResponses.get(0).getId();
    private MovieResponse movieResponse = DataDummy.getMovieResponse(movieId);
    private TvShowResponse tvShowResponse = DataDummy.getTvShowResponse(tvShowId);


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void getMoviesTest() {
        doAnswer(invocation -> {
            ((RemoteRepository.GetMoviesCallback) invocation.getArguments()[0])
                    .onAllMoviesReceived(movieResponses);
            return null;
        }).when(remote).getMovies(any(RemoteRepository.GetMoviesCallback.class));
        List<MovieEntity> result = LiveDataTestUtil.getValue(fakeMovieCatalogueRepository.getMovies());
        verify(remote, times(1)).getMovies(any(RemoteRepository.GetMoviesCallback.class));
        assertNotNull(result);
        assertEquals(movieResponses.size(), result.size());
    }

    @Test
    public void getTvShowTest() {
        doAnswer(invocation -> {
            ((RemoteRepository.GetTvShowCallback) invocation.getArguments()[0])
                    .onAllTvShowReceived(tvShowResponses);
            return null;
        }).when(remote).getTvShows(any(RemoteRepository.GetTvShowCallback.class));
        List<TvShowEntity> result = LiveDataTestUtil.getValue(fakeMovieCatalogueRepository.getTvShow());
        verify(remote, times(1)).getTvShows(any(RemoteRepository.GetTvShowCallback.class));
        assertNotNull(result);
        assertEquals(tvShowResponses.size(), result.size());
    }

    @Test
    public void getDetailMovieTest() {
        doAnswer(invocation -> {
            ((RemoteRepository.GetDetailMovieCallback) invocation.getArguments()[1])
                    .onMovieReceived(movieResponse);
            return null;
        }).when(remote).getMovieDetail(eq(movieId), any(RemoteRepository.GetDetailMovieCallback.class));
        MovieEntity resultContent = LiveDataTestUtil.getValue(fakeMovieCatalogueRepository.getDetailMovie(movieId));
        verify(remote, times(1))
                .getMovieDetail(eq(movieId), any(RemoteRepository.GetDetailMovieCallback.class));

        assertNotNull(resultContent);
        assertNotNull(resultContent.getTitle());
        assertNotNull(resultContent.getGenre());
        assertNotNull(resultContent.getReleaseDate());
        assertNotNull(resultContent.getLanguage());
        assertNotNull(resultContent.getOverview());
        assertNotNull(resultContent.getPosterUrl());
        assertNotNull(resultContent.getCompanies());
        assertNotNull(resultContent.getRating());
        assertEquals(movieResponse.getTitle(), resultContent.getTitle());
    }

    @Test
    public void getDetailTvShowTest() {
        doAnswer(invocation -> {
            ((RemoteRepository.GetDetailTvShowCallback) invocation.getArguments()[1])
                    .onTvShowReceived(tvShowResponse);
            return null;
        }).when(remote).getDetailTvShow(eq(tvShowId), any(RemoteRepository.GetDetailTvShowCallback.class));
        TvShowEntity resultContent = LiveDataTestUtil.getValue(fakeMovieCatalogueRepository.getDetailTvShow(tvShowId));
        verify(remote, times(1))
                .getDetailTvShow(eq(tvShowId), any(RemoteRepository.GetDetailTvShowCallback.class));

        assertNotNull(resultContent);
        assertNotNull(resultContent.getName());
        assertNotNull(resultContent.getGenre());
        assertNotNull(resultContent.getDate());
        assertNotNull(resultContent.getLanguage());
        assertNotNull(resultContent.getOverview());
        assertNotNull(resultContent.getPosterUrl());
        assertNotNull(resultContent.getCompanies());
        assertNotNull(resultContent.getRating());
        assertEquals(tvShowResponse.getName(), resultContent.getName());
    }

    @Test
    public void getFavoriteMoviesTest() {
        MutableLiveData<List<MovieEntity>> listMovie = new MutableLiveData<>();
        List<MovieEntity> movieEntities = DataDummy.generateMovieDummy();
        listMovie.postValue(movieEntities);
        when(local.getFavoriteMovie()).thenReturn(listMovie);
        fakeMovieCatalogueRepository.getFavoriteMovies();
        List<MovieEntity> result = LiveDataTestUtil.getValue(listMovie);
        verify(local).getFavoriteMovie();
        assertNotNull(movieEntities);
        assertEquals(movieEntities.size(), result.size());
    }

    @Test
    public void getFavoriteMovieAsPagedTest() {
        List<MovieEntity> movieEntities = DataDummy.generateMovieDummy();
        when(local.getFavoriteMovieAsPaged()).thenReturn(movieDataSourceFactory);
        fakeMovieCatalogueRepository.getFavoriteMoviesAsPaged();
        PagedList<MovieEntity> result = PagedListUtil.mockPagedList(movieEntities);
        verify(local).getFavoriteMovieAsPaged();
        assertNotNull(result);
        assertEquals(movieEntities.size(), result.size());
    }

    @Test
    public void getFavoriteTvShowTest() {
        MutableLiveData<List<TvShowEntity>> listTvShow = new MutableLiveData<>();
        List<TvShowEntity> tvShowEntities = DataDummy.generateTvShowDummy();
        listTvShow.postValue(tvShowEntities);
        when(local.getFavoriteTvShow()).thenReturn(listTvShow);
        fakeMovieCatalogueRepository.getFavoriteTvShow();
        List<TvShowEntity> result = LiveDataTestUtil.getValue(listTvShow);
        verify(local).getFavoriteTvShow();
        assertNotNull(tvShowEntities);
        assertEquals(tvShowEntities.size(), result.size());
    }

    @Test
    public void getFavoriteTvShowAsPagedTest() {
        List<TvShowEntity> tvShowEntities = DataDummy.generateTvShowDummy();
        when(local.getFavoriteTvShowAsPaged()).thenReturn(tvShowDataSourceFactory);
        fakeMovieCatalogueRepository.getFavoriteTvShowAsPaged();
        PagedList<TvShowEntity> result = PagedListUtil.mockPagedList(tvShowEntities);
        verify(local).getFavoriteTvShowAsPaged();
        assertNotNull(result);
        assertEquals(tvShowEntities.size(), result.size());
    }

}