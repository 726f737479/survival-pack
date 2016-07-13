package com.rostdev.survivalpack;

import android.app.Application;
import android.content.Context;


/**
 * Created by Rosty on 7/5/2016.
 */
public class SurvivalApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule((this)))
                .build();
    }

    public static SurvivalApp get(Context context) {
        return (SurvivalApp) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {

        return component;
    }
}