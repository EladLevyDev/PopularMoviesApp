package com.tikal.realm;

import android.support.annotation.Nullable;

import com.tikal.model.Movie;
import com.tikal.model.MoviesResponse;

import java.util.List;


/**
 * Created by Yoni on 4/5/16.
 */
public interface IAppRepo {

    @Nullable
    boolean saveMoviesResponse(MoviesResponse moviesResponse);

    @Nullable
    List<Movie> getMoviesResponseFromDb();

}
