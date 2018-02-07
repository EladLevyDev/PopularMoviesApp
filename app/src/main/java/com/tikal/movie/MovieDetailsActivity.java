package com.tikal.movie;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;

import com.tikal.R;
import com.tikal.model.Movie;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;


@SuppressLint("Registered")
@EActivity(R.layout.activity_movie_details)
public class MovieDetailsActivity extends AppCompatActivity {

    @Extra(MovieDetailsFragment.MOVIE_ITEM)
    Movie mMovie;

    @AfterViews
    void afterViews() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.movieDetailsFragment, MovieDetailsFragment.newInstance(mMovie, false))
                .commit();
    }

}
