package com.antonicastejon.marvelcharacters.di;

import com.antonicastejon.domain.business.usecases.GetCharactersUseCase;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.main.MainPresenter;
import com.antonicastejon.marvelcharacters.views.main.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

@Module
public class MainPresenterModule {

    private MainView mainView;

    public MainPresenterModule(MainView mainView) {
        this.mainView = mainView;
    }

    @Provides
    MainPresenter providesMainPresenter(GetCharactersUseCase getCharactersUseCase, Images images) {
        return new MainPresenter(mainView, getCharactersUseCase, images);
    }
}
