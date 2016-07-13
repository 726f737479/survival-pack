package com.rostdev.survivalpack.ui.main;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.rostdev.survivalpack.mvp.MvpPresenter;
import com.rostdev.survivalpack.mvp.MvpView;

/**
 * Created by Rosty on 7/5/2016.
 */
public class MainContract {

    interface View extends MvpView {

        void showFragment(Fragment fragment);
        void updateColor(int colorRes, int colorDarkRes);
        void startActivity(Intent intent);
    }

    interface Presenter extends MvpPresenter<View> {

        void onTabSelected(int tabId);
        void onNavSelected(int navId);
    }
}
