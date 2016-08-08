package com.example.modeso_mmac.youtube.services.responses;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Belal Mohamed on 7/28/16.
 * www.modeso.ch
 */
public class Snippet {

    @SerializedName("publishedAt")
    private Date publishDate;

    @SerializedName("channelId")
    private String channelId;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnails")
    private Thumbnails thumbnails;

    @SerializedName("channelTittle")
    private String channelTitle;

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }
}
