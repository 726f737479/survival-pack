package com.rostdev.survivalpack.ui.support;

import android.content.Intent;

import com.rostdev.survivalpack.mvp.MvpPresenter;
import com.rostdev.survivalpack.mvp.MvpView;

/**
 * Created by Rosty on 7/13/2016.
 */
public class SupportContract {

    interface View extends MvpView {

        void startActivity(Intent intent);
        void showFailToast(int messageResId);
        void clearForm();
    }

    interface Presenter extends MvpPresenter<View> {

        void onSendPressed(String subject, String message);
    }
}
