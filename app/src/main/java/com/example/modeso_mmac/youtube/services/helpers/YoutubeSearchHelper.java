package com.example.modeso_mmac.youtube.services.helpers;



import com.example.modeso_mmac.youtube.models.Video;
import com.example.modeso_mmac.youtube.services.httpclientpackage.YoutubeApiClient;
import com.example.modeso_mmac.youtube.services.responses.ApiResponse;
import com.example.modeso_mmac.youtube.services.responses.ErrorResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Belal Mohamed on 7/28/16.
 * www.modeso.ch
 */
public abstract class YoutubeSearchHelper {

    public static void searchVideos(final String searchQuery) {
        YoutubeApiClient.searchYoutube(searchQuery, new YoutubeApiClient.ApiCallBack() {
            @Override
            public void onSuccess(ApiResponse apiResponse) {
                List<Video> videoList = VideoItemWrapper.getVideoList(apiResponse);
                EventBus.getDefault().post(new VideosListEvent(VideosListEvent.EventType.Success, videoList, null, searchQuery));
            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                EventBus.getDefault().post(new VideosListEvent(VideosListEvent.EventType.Error, null, errorResponse.getError().getMessage(), searchQuery));
            }
        });
    }

    public static class VideosListEvent {

        private EventType eventType;
        private List<Video> videos;
        private String errorMessage;
        private String searchQuery;

        public VideosListEvent(EventType eventType, List<Video> videos, String errorMessage, String searchQuery) {
            this.eventType = eventType;
            this.videos = videos;
            this.errorMessage = errorMessage;
            this.searchQuery = searchQuery;
        }

        public enum EventType {
            Success,
            Error;

            public String getName() {
                switch (this) {
                    case Success:
                        return "Success";
                    case Error:
                        return "Error";
                }
                return null;
            }
        }

        public EventType getEventType() {
            return eventType;
        }

        public List<Video> getVideos() {
            return videos;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public String getSearchQuery() {
            return searchQuery;
        }
    }
}
