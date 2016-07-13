package com.rostdev.survivalpack.ui.base;

/**
 * Created by Rosty on 6/2/2016.
 */

import android.support.v4.app.Fragment;

import com.rostdev.survivalpack.SurvivalApp;
import com.rostdev.survivalpack.mvp.BasePresenter;
import com.rostdev.survivalpack.mvp.MvpView;
import com.rostdev.survivalpack.ui.DaggerMvpViewComponent;
import com.rostdev.survivalpack.ui.MvpViewComponent;
import com.rostdev.survivalpack.ui.MvpViewModule;

import javax.inject.Inject;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements MvpView{

    private MvpViewComponent component;

    @Inject
    protected T presenter;

    public MvpViewComponent getComponent() {

        if (component == null)
            component = DaggerMvpViewComponent.builder()
                    .mvpViewModule(new MvpViewModule(getContext()))
                    .applicationComponent(SurvivalApp.get(getContext()).getComponent())
                    .build();

        return component;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        presenter.detachView();
    }
}
