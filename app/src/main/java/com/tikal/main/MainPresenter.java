package com.tikal.main;


import com.tikal.model.Movie;


/**
 * Created by Elad
 */

public interface MainPresenter {

    void loadData();

    void onMovieClicked(Movie movie);
}
