package com.example.modeso_mmac.youtube.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.modeso_mmac.youtube.listeners.VideosListListener;
import com.example.modeso_mmac.youtube.services.ListFetchService;
import com.example.modeso_mmac.youtube.services.helpers.YoutubeSearchHelper;
import com.example.modeso_mmac.youtube.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by Belal Mohamed on 7/29/16.
 * www.modeso.ch
 */
public class VideosListViewModel {

    public Context mContext;
    private VideosListListener mListener;
    private static String currentSearchQuery;
    private boolean mIsPaused;
    private static YoutubeSearchHelper.VideosListEvent mVideosListEvent;

    public VideosListViewModel(@NonNull VideosListListener videosListListener, @NonNull Context context) {
        mContext = context;
        mListener = videosListListener;
    }

    public void onCreate() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void onResume() {
        mIsPaused = false;
        if (currentSearchQuery != null) {
            if (mVideosListEvent == null) {
                requestVideosList(currentSearchQuery);
            } else {
                notifyRegisteredListener(mVideosListEvent);
            }
        }
    }

    public void onPause() {
        mIsPaused = true;
    }


    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void requestVideosList(String searchQuery) {
        currentSearchQuery = searchQuery;
        mVideosListEvent = null;
        Intent i = new Intent(mContext, ListFetchService.class);
        i.setAction(Constants.ACTION_SEARCH_YOUTUBE);
        i.putExtra(Constants.SEARCH_QUERY_KEY, searchQuery);
        mContext.startService(i);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onVideosListEvent(YoutubeSearchHelper.VideosListEvent videosListEvent) {
        if (currentSearchQuery.equals(videosListEvent.getSearchQuery())) {
            mVideosListEvent = videosListEvent;
            if (!mIsPaused) {
                notifyRegisteredListener(videosListEvent);
            }
        }
    }

    private void notifyRegisteredListener(YoutubeSearchHelper.VideosListEvent videosListEvent) {
        switch (videosListEvent.getEventType()) {
            case Success:
                mListener.onListReady(videosListEvent.getVideos());
                break;
            case Error:
                mListener.onError(videosListEvent.getErrorMessage());
                break;
        }
    }

    public String getCurrentSearchQuery() {
        return currentSearchQuery;
    }
}
