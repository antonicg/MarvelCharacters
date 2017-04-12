package com.antonicastejon.marvelcharacters.di;

import com.antonicastejon.marvelcharacters.views.splash.SplashPresenter;
import com.antonicastejon.marvelcharacters.views.splash.SplashView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoni Castejón on 11/04/2017.
 */

@Module
public class SplashPresenterModule {

    private SplashView splashView;

    public SplashPresenterModule(SplashView splashView) {
        this.splashView = splashView;
    }

    @Provides
    SplashPresenter providesSplashPresenter() {
        return new SplashPresenter(splashView);
    }
}
