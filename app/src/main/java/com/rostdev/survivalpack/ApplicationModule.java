package com.rostdev.survivalpack;

import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    @ApplicationContext
    private final Context сontext;

    ApplicationModule(@ApplicationContext Context context) {
        сontext = context;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return сontext;
    }

    @Provides
    @Singleton
    Bus provideBus() { return new Bus(); }
}
