package com.antonicastejon.domain.helpers;

import android.support.annotation.NonNull;

import com.antonicastejon.model.repository.entities.ComicRepository;
import com.antonicastejon.model.repository.entities.ImageRepository;

import java.util.List;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class ComicRepositoryHelper {

    public static String getThumbnailUrl(@NonNull ComicRepository comicRepository) {
        ImageRepository thumbnail = comicRepository.getThumbnail();
        if (thumbnail != null) {
            return getUrl(thumbnail);
        }
        return null;
    }

    public static String getRandomImageUrl(@NonNull ComicRepository comicRepository) {
        List<ImageRepository> imageRepositories = comicRepository.getImageRepositories();
        int size = imageRepositories != null ? imageRepositories.size() : 0;
        if (size == 0) return null;

        int imagePos;
        if (size > 1) imagePos = RandomUtils.getRandomBetween(0, size -1);
        else imagePos = 0;
        return getImageUrl(imageRepositories, imagePos);
    }

    private static String getImageUrl(@NonNull List<ImageRepository> imageRepositories, int imagePos) {
        if (imageRepositories.size() > imagePos) {
            return getUrl(imageRepositories.get(imagePos));
        }
        return null;
    }

    private static String getUrl(@NonNull ImageRepository img) {
        return img.getPath() + "." + img.getExtension();
    }

}
