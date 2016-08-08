package com.example.modeso_mmac.youtube.services.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Belal Mohamed on 7/28/16.
 * www.modeso.ch
 */
public class ApiResponse {

    @SerializedName("nextPageToken")
    private String nextPageToken;

    @SerializedName("regionCode")
    private String regionCode;

    @SerializedName("pageInfo")
    private PageInfo pageInfo;

    @SerializedName("items")
    private List<Item> items;

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
