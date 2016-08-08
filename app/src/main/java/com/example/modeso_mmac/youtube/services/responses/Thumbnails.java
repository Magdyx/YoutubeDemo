package com.example.modeso_mmac.youtube.services.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal Mohamed on 7/28/16.
 * www.modeso.ch
 */
public class Thumbnails {

    @SerializedName("high")
    private High high;

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }
}
