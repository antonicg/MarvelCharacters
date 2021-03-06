package com.antonicastejon.model.repository.services;

import com.antonicastejon.model.repository.Constants;
import com.antonicastejon.model.repository.api.MarvelApi;
import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.crypt.MD5;
import com.antonicastejon.model.repository.entities.CharacterRepository;
import com.antonicastejon.model.repository.services.base.Service;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;

/**
 * Created by Antoni Castejón on 27/01/2017.
 */

public class MarvelService extends Service<CharacterRepository> {

    private final static String TAG = MarvelService.class.getName();

    private final static int REQUEST_LIMIT = 20;

    private MarvelApi marvelApi;

    private int offset;

    @Inject
    public MarvelService(MarvelApi marvelApi, MD5 md5, @Named(Constants.NAMED_PUBLIC_KEY) String publicKey, @Named(Constants.NAMED_PRIVATE_KEY) String privateKey) {
        super(md5, publicKey, privateKey);
        this.marvelApi = marvelApi;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    protected Flowable<ResponseWrapper<CharacterRepository>> executeService(long timeStamp, String hash) {
        return marvelApi.getCharacters(timeStamp, getPublicKey(), hash, REQUEST_LIMIT, offset);
    }
}
