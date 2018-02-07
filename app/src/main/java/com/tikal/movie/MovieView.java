package com.tikal.movie;

import com.tikal.model.Trailer;

import java.util.List;

public interface MovieView {

    void onTrailersLoaded(List<Trailer> value);

    void onTrailersFailure();

    void onTrailerClicked(Trailer trailer);
}