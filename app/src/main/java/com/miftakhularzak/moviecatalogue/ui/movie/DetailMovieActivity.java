package com.miftakhularzak.moviecatalogue.ui.movie;

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
import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.utils.Constants;
import com.miftakhularzak.moviecatalogue.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivPoster;
    private ImageView ivFavorite;
    private TextView tvTitle;
    private TextView tvGenre;
    private TextView tvCompanies;
    private TextView tvLanguage;
    private TextView tvRating;
    private TextView tvDate;
    private TextView tvRuntime;
    private TextView tvOverview;
    private int movieId;
    private MovieViewModel movieViewModel;
    private MovieEntity movie;
    private ProgressBar progressBar;
    private List<MovieEntity> listFavorite = new ArrayList<>();
    private ScrollView layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tvTitle = findViewById(R.id.tv_detail_movie_title);
        tvGenre = findViewById(R.id.tv_detail_movie_genre);
        tvCompanies = findViewById(R.id.tv_detail_movie_companies_production);
        tvLanguage = findViewById(R.id.tv_detail_movie_language);
        tvRating = findViewById(R.id.tv_detail_movie_rating);
        tvDate = findViewById(R.id.tv_detail_movie_date);
        tvRuntime = findViewById(R.id.tv_detail_movie_runtime);
        tvOverview = findViewById(R.id.tv_detail_movie_overview);
        ivPoster = findViewById(R.id.iv_detail_movie_poster);
        ivFavorite = findViewById(R.id.iv_detail_movie_favorite);
        progressBar = findViewById(R.id.pb_movie_detail);
        layout = findViewById(R.id.layout);

        ivFavorite.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            showLoading(true);
            movieId = extras.getInt(Constants.MOVIE_ID);
            movieViewModel = obtainViewModel(this);
            movieViewModel.getMovieDetail(movieId).observe(this, movieModel -> {
                if (movieModel == null || movieModel.getMovieId() != movieId) return;
                movie = movieModel;
                tvTitle.setText(movie.getTitle());
                tvGenre.setText(movie.getGenre());
                tvCompanies.setText(movie.getCompanies());
                tvLanguage.setText(movie.getLanguage());
                tvRating.setText(movie.getRating());
                tvDate.setText(movie.getReleaseDate());
                tvRuntime.setText(movie.getRuntime() + " minutes");
                tvOverview.setText(movie.getOverview());
                Glide.with(this).load(Constants.IMAGE_BASE_URL + movie.getPosterUrl()).into(ivPoster);
                showLoading(false);
            });
            movieViewModel.getFavoriteMovies().observe(this, movieEntities -> {
                listFavorite = movieEntities;
                setFavoriteIcon(isFavorite(movieId));
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) this.finish();
        return true;
    }

    @NonNull
    private static MovieViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }

    void showLoading(boolean isShow) {
        if (isShow) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_detail_movie_favorite) {
            if (!isFavorite(movieId)) {
                movieViewModel.insertFavoriteMovie(movie);
                Snackbar snackbar = Snackbar.make(layout, "Added to Favorite", Snackbar.LENGTH_LONG);
                snackbar.setAction("Favorite", view1 -> goToFavorite());
                snackbar.setActionTextColor(Color.GREEN);
                snackbar.show();
            } else {
                movieViewModel.deleteFavoriteMovie(movie);
                MovieEntity deletedMovie = new MovieEntity(movie.getMovieId(),
                        movie.getTitle(),
                        movie.getOverview(),
                        movie.getReleaseDate(),
                        movie.getPosterUrl(),
                        movie.getGenre(),
                        movie.getCompanies(),
                        movie.getLanguage(),
                        movie.getRating(),
                        movie.getRuntime());
                Snackbar snackbar = Snackbar.make(layout, "Removed from Favorite", Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", view1 -> movieViewModel.insertFavoriteMovie(deletedMovie));
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        }
    }

    boolean isFavorite(int id) {
        for (MovieEntity movie : listFavorite) {
            if (movie.getMovieId() == id) return true;
        }
        return false;
    }

    private void setFavoriteIcon(boolean isFavorite) {
        if (isFavorite) ivFavorite.setImageResource(R.drawable.ic_favorite);
        else ivFavorite.setImageResource(R.drawable.ic_unfavorite);
    }

    private void goToFavorite() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(Constants.IS_FAVORITE_FRAGMENT, true);
        startActivity(intent);
    }

}
