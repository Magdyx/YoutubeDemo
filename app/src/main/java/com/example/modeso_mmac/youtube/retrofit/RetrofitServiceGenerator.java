package com.example.modeso_mmac.youtube.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Belal Mohamed on 8/16/16.
 * www.modeso.ch
 */
public class RetrofitServiceGenerator {

    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";

    private static OkHttpClient.Builder mHttpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder mRetrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <T> T createService(Class<T> serviceClass) {
        Retrofit retrofit = mRetrofitBuilder.client(mHttpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
