package com.tikal.movie;

import android.content.Context;

import com.tikal.model.Trailer;
import com.tikal.model.TrailersResponse;
import com.tikal.network.AppObserver;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@EBean
public class MoviePresenterImpl implements MoviePresenter {

    @Bean
    MovieInteractor mInteractor;

    private MovieView mView;
    private Disposable mDisposable;

    public MoviePresenterImpl(Context context) {

    }

    public void setView(MovieView view) {
        this.mView = view;
    }

    @Override
    public void loadTrailers(String movieId) {
        mInteractor.loadTrailers(movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AppObserver<TrailersResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(TrailersResponse response) {
                        super.onNext(response);
                        if (mView != null && response != null) {
                            mView.onTrailersLoaded(response.getMovies());
                        }
                    }
                });
    }

    @Override
    public void onTrailerClicked(Trailer trailer) {
        if (mView != null) {
            mView.onTrailerClicked(trailer);
        }
    }

    public void onViewDestroyed() {
        if (mDisposable != null && (!mDisposable.isDisposed())) {
            mDisposable.dispose();
        }
    }
}