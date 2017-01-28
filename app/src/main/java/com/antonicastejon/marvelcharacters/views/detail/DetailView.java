package com.antonicastejon.marvelcharacters.views.detail;

import com.antonicastejon.marvelcharacters.utils.image.Images;
import com.antonicastejon.marvelcharacters.views.base.BaseMvpView;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public interface DetailView extends BaseMvpView {

    void showTitle(String title);
    void showDescription(String description);
    void showImage(Images images, String urlImage);
    void showPages(int pages);
}
