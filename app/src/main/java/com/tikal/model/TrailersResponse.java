package com.tikal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersResponse {

    @SerializedName("results")
    @Expose
    public List<Trailer> mTrailerItems;

    public List<Trailer> getMovies() {
        return mTrailerItems;
    }


}