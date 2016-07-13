package com.rostdev.survivalpack.mvp;

/**
 * Created by Rosty on 6/1/2016.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);
    void detachView();
}


