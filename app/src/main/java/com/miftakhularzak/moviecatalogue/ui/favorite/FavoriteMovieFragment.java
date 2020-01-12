package com.miftakhularzak.moviecatalogue.ui.favorite;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miftakhularzak.moviecatalogue.R;
import com.miftakhularzak.moviecatalogue.ui.movie.MovieViewModel;
import com.miftakhularzak.moviecatalogue.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {

    private RecyclerView rvFavorite;
    private MovieViewModel movieViewModel;
    private MoviePagedAdapter pagedAdapter;

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavorite = view.findViewById(R.id.rv_favorite_movie);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pagedAdapter = new MoviePagedAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvFavorite.setLayoutManager(layoutManager);
        movieViewModel = obtainViewModel(getActivity());
        movieViewModel.getFavoriteMoviesAsPaged().observe(this, movieEntities -> {
            if (movieEntities != null) {
                pagedAdapter.submitList(movieEntities);
                pagedAdapter.notifyDataSetChanged();
                rvFavorite.setHasFixedSize(true);
                rvFavorite.setAdapter(pagedAdapter);
            }
        });
    }

    @NonNull
    private MovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }

}
