package com.tikal.movie;


import com.tikal.model.Trailer;


/**
 * Created by Elad
 */

public interface MoviePresenter {

    void loadTrailers(String movieId);

    void onTrailerClicked(Trailer parcelable);
}
