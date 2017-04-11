package com.antonicastejon.marvelcharacters.views.detail;

import android.support.annotation.NonNull;

import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BasePresenter;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class DetailPresenter extends BasePresenter<DetailView> {

    private Images images;

    public DetailPresenter(DetailView mvpView, Images images) {
        super(mvpView);
        this.images = images;
    }

    public void show(@NonNull Character character) {
        DetailView view = getView();
        view.showTitle(character.getTitle());
        view.showDescription(character.getDescription());
        view.showImage(images, character.getThumbnailUrl());
    }
}
