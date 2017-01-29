package com.antonicastejon.marvelcharacters.views.main;

import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BaseMvpView;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public interface MainView extends BaseMvpView {

    void initializeComicsView(Images images);

    void initializeComicsViewWithComics(Images images, List<Comic> comics);

    boolean thereAreAnyComic();

    void updateComics(List<Comic> viewData);

    void errorLoadingComics();

    void showRetryMessage();

    void hideRetryMessage();

    boolean isShowingRetryMessage();
}
