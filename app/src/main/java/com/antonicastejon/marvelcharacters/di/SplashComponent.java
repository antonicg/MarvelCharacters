package com.antonicastejon.marvelcharacters.di;

import com.antonicastejon.marvelcharacters.views.splash.SplashActivity;

import dagger.Component;

/**
 * Created by Antoni Castejón on 11/04/2017.
 */

@Component(modules = {SplashPresenterModule.class})
public interface SplashComponent {

    void inject(SplashActivity splashActivity);
}
