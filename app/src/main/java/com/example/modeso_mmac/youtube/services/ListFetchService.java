package com.example.modeso_mmac.youtube.services;

import android.app.IntentService;
import android.content.Intent;

import com.example.modeso_mmac.youtube.services.helpers.YoutubeSearchHelper;
import com.example.modeso_mmac.youtube.utils.Constants;


/**
 * Created by Belal Mohamed on 7/27/16.
 * www.modeso.ch
 */
public class ListFetchService extends IntentService {

    public ListFetchService() {
        super("List loading service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        switch (intent.getAction()) {
            case Constants.ACTION_SEARCH_YOUTUBE:
                YoutubeSearchHelper.searchVideos(intent.getStringExtra(Constants.SEARCH_QUERY_KEY));
                break;
        }
    }
}
