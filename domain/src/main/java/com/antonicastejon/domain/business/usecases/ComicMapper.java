package com.antonicastejon.domain.business.usecases;

import com.antonicastejon.domain.helpers.ComicHelper;
import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.entities.Comic;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public class ComicMapper implements Function<ResponseWrapper<Comic>, List<com.antonicastejon.domain.business.entities.Comic>> {

    @Inject
    ComicMapper() {}

    @Override
    public List<com.antonicastejon.domain.business.entities.Comic> apply(ResponseWrapper<Comic> comicResponseWrapper) throws Exception {
        List<com.antonicastejon.domain.business.entities.Comic> businessComics = new ArrayList<>();
        ResponseWrapper.DataContainer<Comic> data = comicResponseWrapper.getData();
        if (data != null) {
            List<Comic> results = data.getResults();
            for (Comic comic : results) {
                com.antonicastejon.domain.business.entities.Comic businessComic =
                        new com.antonicastejon.domain.business.entities.Comic(
                                comic.getId(),
                                comic.getTitle(),
                                comic.getDescription(),
                                comic.getPageCount(),
                                ComicHelper.getThumbnailUrl(comic),
                                ComicHelper.getRandomImageUrl(comic)
                        );
                businessComics.add(businessComic);
            }
        }
        return businessComics;
    }
}
