package com.rostdev.survivalpack.ui;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class MvpViewModule {

    @MvpViewContext
    private final Context сontext;

    public MvpViewModule(@MvpViewContext Context context) {
        сontext = context;
    }

    @Provides
    @MvpViewContext
    Context provideContext() {
        return сontext;
    }
}
