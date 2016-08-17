package com.example.modeso_mmac.youtube.viewmodels;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.modeso_mmac.youtube.listeners.VideosListListener;
import com.example.modeso_mmac.youtube.models.Video;
import com.example.modeso_mmac.youtube.retrofit.RetrofitServiceGenerator;
import com.example.modeso_mmac.youtube.retrofit.interfaces.SearchInterface;
import com.example.modeso_mmac.youtube.services.rxjava.YoutubeApiClient;


import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by Belal Mohamed on 7/29/16.
 * www.modeso.ch
 */
public class VideosListViewModel implements Observer<List<Video>> {

    public Context mContext;
    private VideosListListener mListener;
    private static String currentSearchQuery;
    private CompositeSubscription mCompositeSubscription;

    public VideosListViewModel(@NonNull VideosListListener videosListListener, @NonNull Context context) {
        mContext = context;
        mListener = videosListListener;
    }

    public void onResume() {
        mCompositeSubscription = new CompositeSubscription();
        Subscription subscription = YoutubeApiClient.searchYoutube(this);
        if (subscription != null) {
            mCompositeSubscription.add(subscription);
        }
    }

    public void onPause() {
        mCompositeSubscription.unsubscribe();
    }

    public void requestVideosList(String searchQuery) {
        currentSearchQuery = searchQuery;
        Subscription subscription = YoutubeApiClient.searchYoutube(searchQuery, this, RetrofitServiceGenerator.createService(SearchInterface.class));
        if (subscription != null) {
            mCompositeSubscription.add(subscription);
        }
    }

    public String getCurrentSearchQuery() {
        return currentSearchQuery;
    }

    @Override
    public void onCompleted() {
        //This callback is invoked after the observable chain is fully completed without errors.
    }

    @Override
    public void onError(Throwable e) {
        mListener.onError("Error!");
        e.printStackTrace();
    }

    @Override
    public void onNext(List<Video> videos) {
        mListener.onListReady(videos);
    }
}
