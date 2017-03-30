package com.antonicastejon.marvelcharacters.model.helpers;

import android.support.annotation.NonNull;

import com.antonicastejon.marvelcharacters.utils.random.RandomUtils;
import com.antonicastejon.model.repository.entities.Comic;
import com.antonicastejon.model.repository.entities.Image;

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

    public static String getRandomImageUrl(@NonNull Comic comic) {
        List<Image> images = comic.getImages();
        int size = images != null ? images.size() : 0;
        if (size == 0) return null;

        int imagePos;
        if (size > 1) imagePos = RandomUtils.getRandomBetween(0, size -1);
        else imagePos = 0;
        return getImageUrl(images, imagePos);
    }

    private static String getImageUrl(@NonNull List<Image> images, int imagePos) {
        if (images.size() > imagePos) {
            return getUrl(images.get(imagePos));
        }
        return null;
    }

    private static String getUrl(@NonNull Image img) {
        return img.getPath() + "." + img.getExtension();
    }

}
