package com.miftakhularzak.moviecatalogue.ui.tvshow;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.miftakhularzak.moviecatalogue.HomeActivity;
import com.miftakhularzak.moviecatalogue.R;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.miftakhularzak.moviecatalogue.utils.Constants;
import com.miftakhularzak.moviecatalogue.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class DetailTvShowActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivPoster;
    private ImageView ivFavorite;
    private TextView tvName;
    private TextView tvGenre;
    private TextView tvCompanies;
    private TextView tvLanguage;
    private TextView tvRating;
    private TextView tvDate;
    private TextView tvOverview;
    private int tvShowId;
    private TvShowViewModel tvShowViewModel;
    private TvShowEntity tvShow;
    private ProgressBar progressBar;
    private List<TvShowEntity> listFavorite = new ArrayList<>();
    private ScrollView layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tvName = findViewById(R.id.tv_detail_tv_show_name);
        tvGenre = findViewById(R.id.tv_detail_tv_show_genre);
        tvCompanies = findViewById(R.id.tv_detail_tv_show_companies_production);
        tvLanguage = findViewById(R.id.tv_detail_tv_show_language);
        tvRating = findViewById(R.id.tv_detail_tv_show_rating);
        tvDate = findViewById(R.id.tv_detail_tv_show_date);
        tvOverview = findViewById(R.id.tv_detail_tv_show_overview);
        ivPoster = findViewById(R.id.iv_detail_tv_show_poster);
        ivFavorite = findViewById(R.id.iv_detail_tv_show_favorite);
        progressBar = findViewById(R.id.pb_detail_tv_show);
        layout = findViewById(R.id.layout);

        ivFavorite.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            showLoading(true);
            tvShowId = extras.getInt(Constants.TV_ID);
            tvShowViewModel = obtainViewModel(this);
            tvShowViewModel.getTvDetail(tvShowId).observe(this, tvShowEntity -> {
                if(tvShowEntity == null || tvShowEntity.getTvId() != tvShowId) return;
                tvShow = tvShowEntity;
                tvName.setText(tvShow.getName());
                tvGenre.setText(tvShow.getGenre());
                tvCompanies.setText(tvShow.getCompanies());
                tvLanguage.setText(tvShow.getLanguage());
                tvDate.setText(tvShow.getDate());
                tvRating.setText(tvShow.getRating());
                tvOverview.setText(tvShow.getOverview());
                Glide.with(this).load(Constants.IMAGE_BASE_URL + tvShow.getPosterUrl()).into(ivPoster);
                showLoading(false);
            });
            tvShowViewModel.getFavoriteTvShow().observe(this, tvShowEntities -> {
                listFavorite = tvShowEntities;
                setFavoriteIcon(isFavorite(tvShowId));
            });

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) this.finish();
        return true;
    }

    @NonNull
    private static TvShowViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
    }

    void showLoading(boolean isShow) {
        if (isShow) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

    boolean isFavorite(int id) {
        for (TvShowEntity tvShow : listFavorite) {
            if (tvShow.getTvId() == id) return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_detail_tv_show_favorite) {
            if (!isFavorite(tvShowId)) {
                tvShowViewModel.insertFavoriteTvShow(tvShow);
                Snackbar snackbar = Snackbar.make(layout, "Added to Favorite", Snackbar.LENGTH_LONG);
                snackbar.setAction("Favorite", view1 -> goToFavorite());
                snackbar.setActionTextColor(Color.GREEN);
                snackbar.show();
            } else {
                tvShowViewModel.deleteFaoriteTvShow(tvShow);
                TvShowEntity deletedTShow = new TvShowEntity(tvShow.getTvId(),
                        tvShow.getName(),
                        tvShow.getOverview(),
                        tvShow.getPosterUrl(),
                        tvShow.getGenre(),
                        tvShow.getCompanies(),
                        tvShow.getLanguage(),
                        tvShow.getRating(),
                        tvShow.getDate());
                Snackbar snackbar = Snackbar.make(layout, "Removed from Favorite", Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", view1 -> tvShowViewModel.insertFavoriteTvShow(deletedTShow));
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        }
    }

    private void setFavoriteIcon(boolean isFavorite) {
        if (isFavorite) ivFavorite.setImageResource(R.drawable.ic_favorite);
        else ivFavorite.setImageResource(R.drawable.ic_unfavorite);
    }

    private void goToFavorite() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(Constants.IS_FAVORITE_FRAGMENT,true);
        startActivity(intent);
    }
}
