package com.example.modeso_mmac.youtube.services.httpclientpackage;

import android.util.Log;

import com.example.modeso_mmac.youtube.services.responses.ApiResponse;
import com.example.modeso_mmac.youtube.services.responses.Error;
import com.example.modeso_mmac.youtube.services.responses.ErrorResponse;
import com.example.modeso_mmac.youtube.utils.Constants;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Belal Mohamed on 7/28/16.
 * www.modeso.ch
 */
public abstract class YoutubeApiClient {

    public static void searchYoutube(String searchQuery, ApiCallBack apiCallBack) {
        try {
            String encodedSearchQuery = URLEncoder.encode(searchQuery, "UTF-8");
            URL url = new URL(Constants.BASE_URL + encodedSearchQuery);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setRequestProperty("Content-Type", "application/json");
            int code = httpsURLConnection.getResponseCode();
            Gson gson = new Gson();
            if (code == 200 || code == 201) {
                if (apiCallBack != null) {
                    apiCallBack.onSuccess(gson.fromJson(getBodyString(httpsURLConnection.getInputStream()), ApiResponse.class));
                }
            } else {
                if (apiCallBack != null) {
                    ErrorResponse errorResponse = gson.fromJson(getBodyString(httpsURLConnection.getErrorStream()), ErrorResponse.class);
                    apiCallBack.onError(errorResponse);
                }
            }

        } catch (IOException e) {
            if (apiCallBack != null) {
                apiCallBack.onError(getErrorResponse(11111, e));
            }
            e.printStackTrace();
        }
    }

    private static ErrorResponse getErrorResponse(int code, Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        Error error = new Error();
        error.setCode(code);
        error.setMessage(e.getMessage());
        errorResponse.setError(error);
        return errorResponse;
    }

    private static String getBodyString(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public interface ApiCallBack {
        void onSuccess(ApiResponse apiResponse);

        void onError(ErrorResponse errorResponse);
    }
}
