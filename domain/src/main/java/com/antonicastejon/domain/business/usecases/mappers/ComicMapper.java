package com.antonicastejon.domain.business.usecases.mappers;

import com.antonicastejon.domain.business.entities.Comic;
import com.antonicastejon.domain.helpers.ComicRepositoryHelper;
import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.entities.ComicRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public class ComicMapper implements Function<ResponseWrapper<ComicRepository>, List<Comic>> {

    @Inject
    ComicMapper() {}

    @Override
    public List<Comic> apply(ResponseWrapper<ComicRepository> comicResponseWrapper) throws Exception {
        List<Comic> businessComics = new ArrayList<>();
        ResponseWrapper.DataContainer<ComicRepository> data = comicResponseWrapper.getData();
        if (data != null) {
            List<ComicRepository> results = data.getResults();
            for (ComicRepository comicRepository : results) {
                Comic businessComic =
                        new Comic(
                                comicRepository.getId(),
                                comicRepository.getTitle(),
                                comicRepository.getDescription(),
                                comicRepository.getPageCount(),
                                ComicRepositoryHelper.getThumbnailUrl(comicRepository),
                                ComicRepositoryHelper.getRandomImageUrl(comicRepository)
                        );
                businessComics.add(businessComic);
            }
        }
        return businessComics;
    }
}
