package com.antonicastejon.marvelcharacters.utils.image;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.antonicastejon.marvelcharacters.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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
                .placeholder(R.drawable.placeholder_loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(into);
    }

    public void loadWithNoPlaceholder(@NonNull String url, @NonNull ImageView into) {
        Glide.with(into.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(into);
    }
}
