package com.example.modeso_mmac.youtube.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.modeso_mmac.youtube.R;
import com.example.modeso_mmac.youtube.models.Video;

import java.util.List;

/**
 * Created by Belal Mohamed on 8/1/16.
 * www.modeso.ch
 */
public class VideosListRecyclerAdapter extends RecyclerView.Adapter<VideosListRecyclerAdapter.ViewHolder> {

    private List<Video> videoList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Video video = videoList.get(position);
        holder.videoTitle.setText(video.getTitle());
        Glide.with(holder.videoThumbnail.getContext()).load(video.getThumbnails()).centerCrop().
                crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)//cache all forms of the image
                .into(holder.videoThumbnail);
    }

    @Override
    public int getItemCount() {
        return videoList != null ? videoList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView videoThumbnail;
        private TextView videoTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            videoThumbnail = (ImageView) itemView.findViewById(R.id.video_thumbnail);
            videoTitle = (TextView) itemView.findViewById(R.id.video_title);
        }
    }

    public void updateAdapter(List<Video> videos) {
        videoList = videos;
        notifyDataSetChanged();
    }

    public void clearList() {
        if (videoList != null) {
            videoList.clear();
            notifyDataSetChanged();
        }
    }
}
