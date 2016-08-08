package com.example.modeso_mmac.youtube.utils;

/**
 * Created by Belal Mohamed on 7/27/16.
 * www.modeso.ch
 */
public abstract class Constants {
    public static final String API_KEY = "AIzaSyCJ3HopRzh1esnpAXUVmHABOdO3_stuvhs";
    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/search?part=id,snippet&maxResults=50&type=video&key=" + API_KEY + "&q=";
    public static final String ACTION_SEARCH_YOUTUBE = "com.example.modeso_mmac.youtube.ACTION_SEARCH_YOUTUBE";
    public static final String SEARCH_QUERY_KEY = "com.example.modeso_mmac.youtube.SEARCH_QUERY_KEY";
    public static final String SHOW_PROGRESS_DIALOG_KEY = "com.example.modeso_mmac.youtube.SHOW_PROGRESS_DIALOG_KEY";
}
