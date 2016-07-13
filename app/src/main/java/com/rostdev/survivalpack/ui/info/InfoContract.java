package com.rostdev.survivalpack.ui.info;

import com.rostdev.survivalpack.model.SensorInfo;
import com.rostdev.survivalpack.mvp.MvpPresenter;
import com.rostdev.survivalpack.mvp.MvpView;

/**
 * Created by Rosty on 7/5/2016.
 */
public class InfoContract {

    interface View extends MvpView {

        void updateSensorInfoData(SensorInfo[] data);
    }

    interface Presenter extends MvpPresenter<View> {

    }
}
