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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miftakhularzak.moviecatalogue.R;
import com.miftakhularzak.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.miftakhularzak.moviecatalogue.ui.tvshow.DetailTvShowActivity;
import com.miftakhularzak.moviecatalogue.utils.Constants;

public class TvShowPagedAdapter extends PagedListAdapter<TvShowEntity, TvShowPagedAdapter.TvShowViewHolder> {
    private Activity activity;

    public TvShowPagedAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public TvShowPagedAdapter(@NonNull DiffUtil.ItemCallback<TvShowEntity> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TvShowPagedAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tv_show, parent, false);
        Log.d("ROOM_TEST", "onCreateViewHolder: ");
        return new TvShowPagedAdapter.TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowPagedAdapter.TvShowViewHolder holder, int position) {
        final TvShowEntity tvShow = getItem(position);
        holder.tvName.setText(tvShow.getName());
        holder.tvOverview.setText(tvShow.getOverview());
        Glide.with(activity).load(Constants.IMAGE_BASE_URL + tvShow.getPosterUrl()).placeholder(R.drawable.ic_image).into(holder.ivPoster);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(activity, DetailTvShowActivity.class);
            intent.putExtra(Constants.TV_ID, tvShow.getTvId());
            activity.startActivity(intent);
        });
    }

    private static DiffUtil.ItemCallback<TvShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.getTvId() == newItem.getTvId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    TvShowEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
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
