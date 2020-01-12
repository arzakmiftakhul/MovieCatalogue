package com.miftakhularzak.moviecatalogue.ui.tvshow;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miftakhularzak.moviecatalogue.R;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.miftakhularzak.moviecatalogue.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private RecyclerView recyclerView;
    private TvShowAdapter tvShowAdapter;
    private TvShowViewModel tvShowViewModel;
    private List<TvShowEntity> tvShows = new ArrayList<>();
    private ProgressBar progressBar;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvShowAdapter = new TvShowAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tvShowAdapter);

        if (getActivity() != null) {
            showLoading(true);
            tvShowViewModel = obtainViewModel(getActivity());
            tvShowViewModel.getTvShow().observe(this, tvShowEntities -> {
                tvShows = tvShowEntities;
                tvShowAdapter.setTvShows(tvShows);
                recyclerView.setAdapter(tvShowAdapter);
                showLoading(false);
            });

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_tv_show);
        progressBar = view.findViewById(R.id.pb_tv_show);
    }


    @NonNull
    private TvShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
    }

    private void showLoading(boolean isShow) {
        if (isShow) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

}
