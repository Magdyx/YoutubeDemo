package com.example.modeso_mmac.youtube.listeners;

import com.example.modeso_mmac.youtube.models.Video;

import java.util.List;

/**
 * Created by Belal Mohamed on 7/29/16.
 * www.modeso.ch
 */
public interface VideosListListener {
    void onListReady(List<Video> videos);

    void onError(String message);
}
