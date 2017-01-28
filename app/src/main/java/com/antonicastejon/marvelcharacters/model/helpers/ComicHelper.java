package com.antonicastejon.marvelcharacters.model.helpers;

import android.support.annotation.NonNull;

import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.model.Image;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class ComicHelper {

    public static String getThumbnailUrl(@NonNull Comic comic) {
        Image thumbnail = comic.getThumbnail();
        if (thumbnail != null) {
            return getUrl(thumbnail);
        }
        return null;
    }

    public static String getImageUrl(@NonNull Comic comic, int imagePos) {
        List<Image> images = comic.getImages();
        if (images != null && images.size() > imagePos) {
            return getUrl(images.get(imagePos));
        }
        return null;
    }

    private static String getUrl(@NonNull Image img) {
        return img.getPath() + "." + img.getExtension();
    }

}
