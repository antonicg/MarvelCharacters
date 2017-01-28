package com.antonicastejon.marvelcharacters.views.main;

import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.utils.image.Images;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public interface MainView {

    void initializeView(Images images, List<Comic> viewData);
    void updateComics();
}
