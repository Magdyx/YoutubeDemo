package com.example.modeso_mmac.youtube.services.helpers;

import com.example.modeso_mmac.youtube.models.Video;
import com.example.modeso_mmac.youtube.services.responses.ApiResponse;
import com.example.modeso_mmac.youtube.services.responses.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal Mohamed on 7/29/16.
 * www.modeso.ch
 */
public abstract class VideoItemWrapper {
    public static List<Video> getVideoList(ApiResponse apiResponse) {
        List<Video> videoList = new ArrayList<>();
        for (Item item : apiResponse.getItems()) {
            Video video = new Video();
            video.setId(item.getId().getVideoId());
            video.setPublishDate(item.getSnippet().getPublishDate());
            video.setChannelTitle(item.getSnippet().getChannelTitle());
            video.setTitle(item.getSnippet().getTitle());
            video.setThumbnails(item.getSnippet().getThumbnails().getHigh().getUrl());
            video.setDescription(item.getSnippet().getDescription());
            videoList.add(video);
        }
        return videoList;
    }
}
