package com.tikal.network;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Elad.
 */
public abstract class AppObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
