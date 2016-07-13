package com.rostdev.survivalpack.ui.level;

import com.rostdev.survivalpack.mvp.MvpPresenter;
import com.rostdev.survivalpack.mvp.MvpView;

/**
 * Created by Rosty on 7/5/2016.
 */
public class LevelContract {

    interface View extends MvpView {

        void updateLevelData(float gravityX, float gravityY);
    }

    interface Presenter extends MvpPresenter<View> {

    }
}
