package com.antonicastejon.domain.business.usecases;

import android.support.annotation.NonNull;

import com.antonicastejon.domain.RequestConsumer;
import com.antonicastejon.domain.business.entities.Character;
import com.antonicastejon.domain.business.usecases.base.UseCase;
import com.antonicastejon.domain.business.usecases.mappers.CharacterMapper;
import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.entities.CharacterRepository;
import com.antonicastejon.model.repository.services.MarvelService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public class GetCharactersUseCase extends UseCase<List<Character>> {

    @Inject
    MarvelService marvelService;

    @Inject
    CharacterMapper characterMapper;

    private int totalItems;

    @Inject
    GetCharactersUseCase() {}

    @Override
    protected void executeRequest(RequestConsumer<List<Character>> callback) {
        marvelService.execute()
                .subscribeOn(Schedulers.io())
                .map(this::setTotalItems)
                .map(characterMapper)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback, throwable -> onError(throwable, callback));
    }

    public void setOffset(int offset) {
        marvelService.setOffset(offset);
    }

    private ResponseWrapper<CharacterRepository> setTotalItems(@NonNull ResponseWrapper<CharacterRepository> response) {
        ResponseWrapper.DataContainer<?> data = response.getData();
        if (data != null) this.totalItems = data.getTotal();
        else this.totalItems = 0;
        return response;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
