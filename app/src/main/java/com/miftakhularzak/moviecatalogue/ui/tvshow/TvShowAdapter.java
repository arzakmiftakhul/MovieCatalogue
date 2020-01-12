package com.miftakhularzak.moviecatalogue.ui.tvshow;

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
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.miftakhularzak.moviecatalogue.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private final Activity activity;
    private List<TvShowEntity>  mTvShows = new ArrayList<>();

    public TvShowAdapter(Activity activity) {
        this.activity = activity;
    }

    public List<TvShowEntity> getmTvShows() {
        return mTvShows;
    }

    public void setTvShows(List<TvShowEntity> mTvShows) {
        if(mTvShows != null) {
            this.mTvShows.clear();
            this.mTvShows = mTvShows;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public TvShowAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tv_show,parent,false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TvShowViewHolder holder, int position) {
        holder.tvName.setText(mTvShows.get(position).getName());
        holder.tvOverview.setText(mTvShows.get(position).getOverview());
        Glide.with(activity).load(Constants.IMAGE_BASE_URL+mTvShows.get(position).getPosterUrl()).placeholder(R.drawable.ic_image).into(holder.ivPoster);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity,DetailTvShowActivity.class);
            intent.putExtra(Constants.TV_ID,mTvShows.get(position).getTvId());
            activity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvOverview;
        private ImageView ivPoster;
        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_items_tv_name);
            tvOverview = itemView.findViewById(R.id.tv_items_tv_overview);
            ivPoster = itemView.findViewById(R.id.iv_items_tv_poster);
        }
    }
}
