package com.tikal.network;


import com.tikal.model.MoviesResponse;
import com.tikal.model.TrailersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Elad
 */

public interface ServerApi {

    @GET("movie/popular")
    Observable<MoviesResponse> getTopMoviesResponse(@Query("api_key") String api_key);

    @GET("movie/{id}/videos")
    Observable<TrailersResponse> getTrailersResponse(@Path("id") String id, @Query("api_key") String api_key);

}