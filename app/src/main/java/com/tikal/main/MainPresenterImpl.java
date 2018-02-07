package com.tikal.main;

import android.content.Context;

import com.tikal.model.Movie;
import com.tikal.model.MoviesResponse;
import com.tikal.network.AppObserver;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@EBean
public class MainPresenterImpl implements MainPresenter {

    @Bean
    MainInteractor mInteractor;

    private MainView mView;
    private Disposable mDisposable;

    public MainPresenterImpl(Context context) {

    }

    public void setView(MainView view) {
        this.mView = view;
    }

    @Override
    public void loadData() {
        mInteractor.getMoviesFromServer()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AppObserver<MoviesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(MoviesResponse response) {
                        super.onNext(response);
                        if (mView != null && response != null) {
                            mView.onDataIsReady(response.getMovies());
                        }
                    }
                });
    }

    @Override
    public void onMovieClicked(Movie movie) {
        if (mView != null) {
            mView.showItemDetails(movie);
        }
    }

    public void onViewDestroyed() {
        if (mDisposable != null && (!mDisposable.isDisposed())) {
            mDisposable.dispose();
        }
    }
}