package com.antonicastejon.marvelcharacters.di;

import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.detail.DetailPresenter;
import com.antonicastejon.marvelcharacters.views.detail.DetailView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

@Module
public class DetailPresenterModule {

    private final DetailView detailView;

    public DetailPresenterModule(DetailView detailView) {
        this.detailView = detailView;
    }

    @Provides
    DetailPresenter providesDetailPresenter(Images images) {
        return new DetailPresenter(detailView, images);
    }
}
