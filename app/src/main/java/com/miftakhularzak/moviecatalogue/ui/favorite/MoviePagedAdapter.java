package com.miftakhularzak.moviecatalogue.ui.favorite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miftakhularzak.moviecatalogue.R;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.MovieEntity;
import com.miftakhularzak.moviecatalogue.ui.movie.DetailMovieActivity;
import com.miftakhularzak.moviecatalogue.utils.Constants;

public class MoviePagedAdapter extends PagedListAdapter<MovieEntity, MoviePagedAdapter.MovieViewHolder> {

    private  Activity activity;

    public MoviePagedAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public MoviePagedAdapter(@NonNull ItemCallback<MovieEntity> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        Log.d("ROOM_TEST", "onCreateViewHolder: ");
        return new MoviePagedAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final MovieEntity mMovies = getItem(position);
        holder.tvTitle.setText(mMovies.getTitle());
        holder.tvOverview.setText(mMovies.getOverview());
        holder.tvDate.setText(mMovies.getReleaseDate());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(Constants.MOVIE_ID, mMovies.getMovieId());
            activity.startActivity(intent);
        });
        Glide.with(activity)
                .load(Constants.IMAGE_BASE_URL + mMovies.getPosterUrl())
                .placeholder(R.drawable.ic_image)
                .into(holder.ivPoster);
    }

    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getMovieId()==newItem.getMovieId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    MovieEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
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