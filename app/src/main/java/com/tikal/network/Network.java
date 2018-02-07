package com.tikal.network;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tikal.BuildConfig;

import org.androidannotations.annotations.EBean;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Elad
 */

@EBean(scope = EBean.Scope.Singleton)
public class Network {
    private final ServerApi api;

    Network(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(logging)
                .cache(new Cache(context.getCacheDir(), 100 * 1024 * 1024))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        api = retrofit.create(ServerApi.class);
    }


    public ServerApi api() {
        return api;
    }
}