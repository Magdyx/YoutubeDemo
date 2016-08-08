package com.example.modeso_mmac.youtube.services.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal Mohamed on 7/28/16.
 * www.modeso.ch
 */
public class Error {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int errorCode) {
        this.code = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String errorMessage) {
        this.message = errorMessage;
    }
}
