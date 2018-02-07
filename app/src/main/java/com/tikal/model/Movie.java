package com.tikal.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import io.realm.RealmObject;

/**
 * Created by Elad
 */

@Parcel
public class Movie extends RealmObject {

    String title;
    String vote_average;
    String id;
    String poster_path;
    String backdrop_path;
    String release_date;
    String overview;

    @ParcelConstructor
    public Movie(String title, String vote_average, String id, String poster_path, String backdrop_path, String release_date, String overview) {
        this.title = title;
        this.vote_average = vote_average;
        this.id = id;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.overview = overview;
    }

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getId() {
        return id;
    }


    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }


    public String getBackdrop_path() {
        return backdrop_path;
    }

}
