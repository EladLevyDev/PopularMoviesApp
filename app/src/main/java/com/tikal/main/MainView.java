package com.tikal.main;

import com.tikal.model.Movie;

import java.util.List;

public interface MainView {

    void onDataIsReady(List<Movie> value);

    void onDataIsFailure();

    void showItemDetails(Movie item);
}