package com.tikal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("results")
    @Expose
    public List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }


}