package com.antonicastejon.marvelcharacters.utils.image;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class Images {

    @Inject
    public Images() {}

    public void load(@NonNull String url, @NonNull ImageView into) {
        Glide.with(into.getContext())
                .load(url)
                .into(into);
    }
}
