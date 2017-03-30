package com.antonicastejon.marvelcharacters.di;

import com.antonicastejon.marvelcharacters.views.main.MainActivity;
import com.antonicastejon.model.repository.api.NetModule;

import dagger.Component;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

@Component(modules = {ApplicationModule.class, MainPresenterModule.class, NetModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
