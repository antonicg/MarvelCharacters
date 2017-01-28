package com.antonicastejon.marvelcharacters.di;

import com.antonicastejon.marvelcharacters.views.main.MainActivity;

import dagger.Component;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

@Component(modules = {MainPresenterModule.class, NetModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}