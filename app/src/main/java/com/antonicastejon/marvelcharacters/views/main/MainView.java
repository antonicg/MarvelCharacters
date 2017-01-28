package com.antonicastejon.marvelcharacters.views.main;

import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BaseMvpView;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public interface MainView extends BaseMvpView {

    void initializeComicRecyclerView(Images images, List<Comic> viewData);
    void updateComics();
    void errorLoadingComics();
}