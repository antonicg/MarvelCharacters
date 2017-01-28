package com.antonicastejon.marvelcharacters.di;

import com.antonicastejon.marvelcharacters.views.detail.DetailActivity;

import dagger.Component;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

@Component(modules = DetailPresenterModule.class)
public interface DetailComponent {

    void inject(DetailActivity detailActivity);
}
