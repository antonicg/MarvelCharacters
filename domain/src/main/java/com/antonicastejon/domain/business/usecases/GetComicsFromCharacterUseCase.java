package com.antonicastejon.domain.business.usecases;

import com.antonicastejon.domain.RequestConsumer;
import com.antonicastejon.domain.business.entities.Comic;
import com.antonicastejon.domain.business.usecases.base.UseCase;
import com.antonicastejon.model.repository.services.MarvelService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public class GetComicsFromCharacterUseCase extends UseCase<List<Comic>> {

    @Inject
    MarvelService marvelService;

    @Inject
    ComicMapper comicMapper;

    @Inject
    public GetComicsFromCharacterUseCase() {}

    @Override
    protected void executeRequest(RequestConsumer<List<Comic>> callback) {
        marvelService.execute()
                .subscribeOn(Schedulers.io())
                .map(comicMapper)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback, throwable -> onError(throwable, callback));
    }

    public void setOffset(int offset) {
        marvelService.setOffset(offset);
    }
}
