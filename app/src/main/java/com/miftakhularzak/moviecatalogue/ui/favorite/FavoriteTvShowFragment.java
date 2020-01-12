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
import com.miftakhularzak.moviecatalogue.ui.tvshow.TvShowViewModel;
import com.miftakhularzak.moviecatalogue.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvShowFragment extends Fragment {

    private RecyclerView rvFavorite;
    private TvShowPagedAdapter pagedAdapter;
    private TvShowViewModel tvShowViewModel;

    public FavoriteTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavorite = view.findViewById(R.id.rv_favorite_tv_show);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pagedAdapter = new TvShowPagedAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvFavorite.setLayoutManager(layoutManager);
        tvShowViewModel = obtainViewModel(getActivity());
        tvShowViewModel.getFavoriteTvShowAsPaged().observe(this, tvShowEntities -> {
            if (tvShowEntities != null) {
                pagedAdapter.submitList(tvShowEntities);
                pagedAdapter.notifyDataSetChanged();
                rvFavorite.setAdapter(pagedAdapter);
            }

        });
    }


    @NonNull
    private TvShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
    }

}
