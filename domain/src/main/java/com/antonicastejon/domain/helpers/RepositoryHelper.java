package com.antonicastejon.domain.helpers;

import android.support.annotation.NonNull;

import com.antonicastejon.model.repository.entities.CharacterRepository;
import com.antonicastejon.model.repository.entities.ImageRepository;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class RepositoryHelper {

    public static String getThumbnailUrl(@NonNull CharacterRepository characterRepository) {
        ImageRepository thumbnail = characterRepository.getThumbnail();
        if (thumbnail != null) {
            return getUrl(thumbnail);
        }
        return null;
    }

    private static String getUrl(@NonNull ImageRepository img) {
        return img.getPath() + "." + img.getExtension();
    }

}
