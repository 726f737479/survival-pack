package com.rostdev.survivalpack.ui.light;

import android.content.Context;

import com.rostdev.survivalpack.mvp.BasePresenter;
import com.rostdev.survivalpack.ui.MvpViewContext;
import com.rostdev.survivalpack.util.EasyFlashLight;

import javax.inject.Inject;

/**
 * Created by Rosty on 7/5/2016.
 */
public class LightPresenter extends BasePresenter<LightContract.View> implements LightContract.Presenter{

    private EasyFlashLight easyLight;
    private boolean light;

    @Inject
    public LightPresenter(@MvpViewContext Context context) {

        easyLight = new EasyFlashLight(context);
    }

    @Override
    protected void onViewAttached(LightContract.View View) {
    }

    @Override
    protected void onViewDetached(LightContract.View View) {
        easyLight.release();
    }

    @Override
    public void onLightSwitchEvent() {

        easyLight.toggle(light = !light);
        view.toggleLightIcon(light);
    }
}
