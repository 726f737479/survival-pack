package com.rostdev.survivalpack;

import android.content.Context;
import com.squareup.otto.Bus;

import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by Rosty on 6/1/2016.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Bus bus();
}
