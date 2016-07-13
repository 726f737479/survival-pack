package com.rostdev.survivalpack.ui.support;

import android.content.Context;
import android.content.Intent;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.mvp.BasePresenter;
import com.rostdev.survivalpack.ui.MvpViewContext;
import com.rostdev.survivalpack.util.AppSystemUtil;

import javax.inject.Inject;

/**
 * Created by Rosty on 7/13/2016.
 */
public class SupportPresenter extends BasePresenter<SupportContract.View> implements
        SupportContract.Presenter{

    private Context context;

    @Inject
    public SupportPresenter(@MvpViewContext Context context) {
        this.context = context;
    }

    @Override
    protected void onViewAttached(SupportContract.View View) {}

    @Override
    protected void onViewDetached(SupportContract.View View) {}

    @Override
    public void onSendPressed(String subject, String message) {

        Intent intent = AppSystemUtil.getEmailAppIntent(context, subject, message);

        if (intent != null) {

            view.clearForm();
            view.startActivity(intent);

        }else view.showFailToast(R.string.snack_no_mail);
    }
}
