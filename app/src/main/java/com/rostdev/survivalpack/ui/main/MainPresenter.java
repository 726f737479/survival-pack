package com.rostdev.survivalpack.ui.main;

import android.content.Context;
import android.content.Intent;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.mvp.BasePresenter;
import com.rostdev.survivalpack.ui.MvpViewContext;
import com.rostdev.survivalpack.ui.compass.CompassFragment;
import com.rostdev.survivalpack.ui.info.InfoFragment;
import com.rostdev.survivalpack.ui.level.LevelFragment;
import com.rostdev.survivalpack.ui.light.LightFragment;
import com.rostdev.survivalpack.ui.support.SupportActivity;

import javax.inject.Inject;

/**
 * Created by Rosty on 7/5/2016.
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private int tabId;
    private Context context;

    @Inject
    public MainPresenter(@MvpViewContext Context context) {
        this.context = context;
    }

    @Override
    protected void onViewAttached(MainContract.View View) {
        onTabSelected(tabId);
    }

    @Override
    protected void onViewDetached(MainContract.View View) {}

    @Override
    public void onTabSelected(int tabId) {

        this.tabId = tabId;
        if (view == null) return;

        switch (tabId){

            case R.id.tab_light:
                view.showFragment(new LightFragment());
                view.updateColor(R.color.tab_light, R.color.tab_light_dark);
                break;

            case R.id.tab_compass:
                view.showFragment(new CompassFragment());
                view.updateColor(R.color.tab_compass, R.color.tab_compass_dark);
                break;

            case R.id.tab_level:
                view.showFragment(new LevelFragment());
                view.updateColor(R.color.tab_level, R.color.tab_level_dark);
                break;

            case R.id.tab_info:
                view.showFragment(new InfoFragment());
                view.updateColor(R.color.tab_info, R.color.tab_info_dark);
                break;
        }
    }

    @Override
    public void onNavSelected(int navId) {

        switch (navId){

            case R.id.support:
                view.startActivity(new Intent(context, SupportActivity.class));
                break;

            case R.id.about:
        }
    }
}
