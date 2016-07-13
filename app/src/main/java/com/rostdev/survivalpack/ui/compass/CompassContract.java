package com.rostdev.survivalpack.ui.compass;

import com.rostdev.survivalpack.mvp.MvpPresenter;
import com.rostdev.survivalpack.mvp.MvpView;

/**
 * Created by Rosty on 7/5/2016.
 */
public class CompassContract {

    interface View extends MvpView {

        void updateCompassData(float position);
    }

    interface Presenter extends MvpPresenter<View> {

    }
}
