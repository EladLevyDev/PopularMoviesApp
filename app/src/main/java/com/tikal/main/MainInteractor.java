package com.tikal.main;


import android.content.Context;

import com.tikal.BuildConfig;
import com.tikal.model.MoviesResponse;
import com.tikal.network.Network;
import com.tikal.realm.ReposModule;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.Observable;

/**
 * Created by Elad
 */
@EBean
public class MainInteractor {

    @Bean
    Network mNetwork;

    @Bean
    ReposModule mReposModule;
    ;

    public MainInteractor(Context context) {
    }

    /*
        Server API Call
     */
    public Observable<MoviesResponse> getMoviesFromServer() {
        return mNetwork.api().getTopMoviesResponse(BuildConfig.API_KEY);
    }

    /*
        Save all movies in DB
     */
    public boolean syncMoviesInDb(MoviesResponse response) {
        mReposModule.appRepo().saveMoviesResponse(response);
        return false;
    }

}