package com.antonicastejon.domain.business.usecases;

import android.support.annotation.NonNull;

import com.antonicastejon.domain.RequestConsumer;
import com.antonicastejon.domain.business.entities.Comic;
import com.antonicastejon.domain.business.usecases.base.UseCase;
import com.antonicastejon.model.repository.api.ResponseWrapper;
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

    private int totalItems;

    @Inject
    GetComicsFromCharacterUseCase() {}

    @Override
    protected void executeRequest(RequestConsumer<List<Comic>> callback) {
        marvelService.execute()
                .subscribeOn(Schedulers.io())
                .map(this::setTotalItems)
                .map(comicMapper)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback, throwable -> onError(throwable, callback));
    }

    public void setOffset(int offset) {
        marvelService.setOffset(offset);
    }

    private ResponseWrapper<com.antonicastejon.model.repository.entities.Comic> setTotalItems(@NonNull ResponseWrapper<com.antonicastejon.model.repository.entities.Comic> response) {
        ResponseWrapper.DataContainer<?> data = response.getData();
        if (data != null) this.totalItems = data.getTotal();
        else this.totalItems = 0;
        return response;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
