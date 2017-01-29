package com.antonicastejon.marvelcharacters.utils.image;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import javax.inject.Inject;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class Images {

    @Inject
    public Images() {}

    public void loadForCell(@NonNull String url, @NonNull ImageView into) {
        Glide.with(into.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(into);
    }

    public void loadForDetail(@NonNull String url, @NonNull ImageView into) {
        Glide.with(into.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(into);
    }
}
