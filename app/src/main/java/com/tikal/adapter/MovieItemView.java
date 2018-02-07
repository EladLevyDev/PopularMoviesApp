package com.tikal.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tikal.R;
import com.tikal.model.Movie;
import com.tikal.utils.Utils;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.movie_item)
public class MovieItemView extends LinearLayout {

    @ViewById(R.id.imageView)
    ImageView imageView;

    public MovieItemView(Context context) {
        super(context);
    }

    public void bind(Movie movie) {

        if (movie != null) {
            Utils.loadImageUrl(getContext(), movie.getPoster_path(), imageView);
        }
    }
}