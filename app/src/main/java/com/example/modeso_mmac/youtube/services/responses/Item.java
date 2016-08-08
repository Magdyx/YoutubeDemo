package com.example.modeso_mmac.youtube.services.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal Mohamed on 7/28/16.
 * www.modeso.ch
 */
public class Item {
    @SerializedName("id")
    private Id id;

    @SerializedName("snippet")
    private Snippet snippet;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}
