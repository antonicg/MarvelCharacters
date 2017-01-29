package com.antonicastejon.marvelcharacters.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

@Module
public class ApplicationModule {

    private final Application app;

    public ApplicationModule(Application app) {
        this.app = app;
    }

    @Provides
    Context providesApplication() {
        return app;
    }
}
