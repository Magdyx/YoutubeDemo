package com.example.modeso_mmac.youtube.services.rxjava;


import com.example.modeso_mmac.youtube.models.Video;
import com.example.modeso_mmac.youtube.retrofit.interfaces.SearchInterface;
import com.example.modeso_mmac.youtube.services.helpers.VideoItemWrapper;
import com.example.modeso_mmac.youtube.services.responses.ApiResponse;
import com.example.modeso_mmac.youtube.utils.Constants;

import java.util.List;


import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Belal Mohamed on 7/28/16.
 * www.modeso.ch
 */
public abstract class YoutubeApiClient {

    private static Observable<List<Video>> mCachedObservable;

    //Subscribe a new observable and cache it.
    public static Subscription searchYoutube(String searchQuery, Observer<List<Video>> observerViewModel, SearchInterface searchInterface) {
        mCachedObservable = searchInterface.searchYoutube(searchQuery, "id,snippet", 50, "video", Constants.API_KEY)
                .map(new Func1<ApiResponse, List<Video>>() {
                    @Override
                    public List<Video> call(ApiResponse apiResponse) {
                        return VideoItemWrapper.getVideoList(apiResponse);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .cache();

        return mCachedObservable.subscribe(observerViewModel);
    }

    //Subscribe the cached observable.
    public static Subscription searchYoutube(Observer<List<Video>> observerViewModel) {
        return mCachedObservable != null ? mCachedObservable.subscribe(observerViewModel) : null;
    }
}
