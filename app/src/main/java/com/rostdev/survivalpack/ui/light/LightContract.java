package com.rostdev.survivalpack.ui.light;

import com.rostdev.survivalpack.mvp.MvpPresenter;
import com.rostdev.survivalpack.mvp.MvpView;

/**
 * Created by Rosty on 7/5/2016.
 */
public class LightContract {

    interface View extends MvpView {

        void toggleLightIcon(boolean light);
    }

    interface Presenter extends MvpPresenter<View> {

        void onLightSwitchEvent();
    }
}
