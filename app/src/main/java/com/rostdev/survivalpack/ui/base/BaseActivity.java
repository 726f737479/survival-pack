package com.rostdev.survivalpack.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.rostdev.survivalpack.SurvivalApp;
import com.rostdev.survivalpack.mvp.BasePresenter;
import com.rostdev.survivalpack.mvp.MvpView;
import com.rostdev.survivalpack.ui.DaggerMvpViewComponent;
import com.rostdev.survivalpack.ui.MvpViewComponent;
import com.rostdev.survivalpack.ui.MvpViewModule;

import javax.inject.Inject;

/**
 * Created by Rosty on 7/6/2016.
 */
public abstract class BaseActivity<T extends BasePresenter>  extends AppCompatActivity implements MvpView{

    private MvpViewComponent component;

    @Inject
    protected T presenter;

    public MvpViewComponent getComponent() {

        if (component == null)
            component = DaggerMvpViewComponent.builder()
                    .mvpViewModule(new MvpViewModule(this))
                    .applicationComponent(SurvivalApp.get(this).getComponent())
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
