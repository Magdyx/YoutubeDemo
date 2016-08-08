package com.example.modeso_mmac.youtube.services.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal Mohamed on 7/28/16.
 * www.modeso.ch
 */
public class PageInfo {

    @SerializedName("totalResults")
    long totalResults;

    @SerializedName("resultsPerPage")
    int resultsPerPage;

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
}
