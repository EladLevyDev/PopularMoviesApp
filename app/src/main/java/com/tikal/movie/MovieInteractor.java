package com.tikal.movie;


import android.content.Context;

import com.tikal.BuildConfig;
import com.tikal.model.TrailersResponse;
import com.tikal.network.Network;
import com.tikal.realm.ReposModule;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.Observable;

/**
 * Created by Elad
 */
@EBean
public class MovieInteractor {

    @Bean
    Network mNetwork;

    @Bean
    ReposModule mReposModule;

    public MovieInteractor(Context context) {

    }


    public Observable<TrailersResponse> loadTrailers(String movieId) {
        return mNetwork.api().getTrailersResponse(movieId, BuildConfig.API_KEY);
    }


}