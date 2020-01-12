package com.miftakhularzak.moviecatalogue.ui.movie;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miftakhularzak.moviecatalogue.R;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieEntity> mMovies = new ArrayList<>();
    private final Activity activity;

    public MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<MovieEntity> getmMovies() {
        return mMovies;
    }

    public void setMovies(List<MovieEntity> mMovies) {
        if (mMovies == null) return;
        this.mMovies.clear();
        this.mMovies.addAll(mMovies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.tvTitle.setText(mMovies.get(position).getTitle());
        holder.tvOverview.setText(mMovies.get(position).getOverview());
        holder.tvDate.setText(mMovies.get(position).getReleaseDate());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(Constants.MOVIE_ID, mMovies.get(position).getMovieId());
            activity.startActivity(intent);
        });
        Glide.with(activity)
                .load(Constants.IMAGE_BASE_URL + mMovies.get(position).getPosterUrl())
                .placeholder(R.drawable.ic_image)
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvOverview;
        private TextView tvDate;
        private ImageView ivPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_items_movie_title);
            tvOverview = itemView.findViewById(R.id.tv_items_movie_overview);
            tvDate = itemView.findViewById(R.id.tv_items_movie_date);
            ivPoster = itemView.findViewById(R.id.iv_items_movie_poster);
        }
    }
}
