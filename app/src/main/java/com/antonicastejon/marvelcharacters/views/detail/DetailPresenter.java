package com.antonicastejon.marvelcharacters.views.detail;

import android.support.annotation.NonNull;

import com.antonicastejon.marvelcharacters.model.helpers.ComicHelper;
import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BasePresenter;
import com.antonicastejon.model.repository.entities.Comic;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class DetailPresenter extends BasePresenter<DetailView> {

    private Images images;

    public DetailPresenter(DetailView mvpView, Images images) {
        super(mvpView);
        this.images = images;
    }

    public void showComic(@NonNull Comic comic) {
        DetailView view = getView();
        view.showTitle(comic.getTitle());
        view.showDescription(comic.getDescription());
        view.showPages(comic.getPageCount());
        view.showImage(images, ComicHelper.getRandomImageUrl(comic));
    }
}
