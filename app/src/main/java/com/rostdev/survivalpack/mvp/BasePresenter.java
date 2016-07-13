package com.rostdev.survivalpack.mvp;

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V view;

    @Override
    public void attachView(V mvpView) {

        if (view == null) onViewAttached(view = mvpView);
    }

    @Override
    public void detachView() {

        if (view != null) onViewDetached(view = null);
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getMvpView() {
        return view;
    }

    protected abstract void onViewAttached(V View);
    protected abstract void onViewDetached(V View);
}