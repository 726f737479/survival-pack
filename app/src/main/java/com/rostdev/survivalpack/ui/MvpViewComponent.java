package com.rostdev.survivalpack.ui;


import com.rostdev.survivalpack.ApplicationComponent;
import com.rostdev.survivalpack.ui.compass.CompassFragment;
import com.rostdev.survivalpack.ui.info.InfoFragment;
import com.rostdev.survivalpack.ui.level.LevelFragment;
import com.rostdev.survivalpack.ui.light.LightFragment;
import com.rostdev.survivalpack.ui.main.MainActivity;
import com.rostdev.survivalpack.ui.support.SupportActivity;

import dagger.Component;

@PerMvpView
@Component(dependencies = ApplicationComponent.class, modules = MvpViewModule.class)
public interface MvpViewComponent {

    void inject(LevelFragment fragment);
    void inject(InfoFragment fragment);
    void inject(CompassFragment fragment);
    void inject(LightFragment fragment);

    void inject(MainActivity activity);
    void inject(SupportActivity activity);
}