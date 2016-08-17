package com.example.modeso_mmac.youtube.retrofit.interfaces;

import com.example.modeso_mmac.youtube.services.responses.ApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Belal Mohamed on 8/16/16.
 * www.modeso.ch
 */
public interface SearchInterface {
    @GET("search")
    Observable<ApiResponse> searchYoutube(@Query("q") String searchQuery, @Query("part") String parts, @Query("maxResults") int maxResults, @Query("type") String type, @Query("key") String apiKey);
}
