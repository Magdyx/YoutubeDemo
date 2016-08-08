package com.example.modeso_mmac.youtube.services.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal Mohamed on 7/28/16.
 * www.modeso.ch
 */
public class ErrorResponse {

    @SerializedName("error")
    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
