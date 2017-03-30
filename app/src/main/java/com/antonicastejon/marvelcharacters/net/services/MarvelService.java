package com.antonicastejon.marvelcharacters.net.services;

import android.content.Context;

import com.antonicastejon.model.repository.Constants;
import com.antonicastejon.marvelcharacters.net.requests.base.RequestConsumer;
import com.antonicastejon.model.repository.crypt.MD5;
import com.antonicastejon.marvelcharacters.utils.network.NetworkStateHelper;
import com.antonicastejon.model.repository.api.MarvelApi;
import com.antonicastejon.model.repository.entities.Comic;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoni Castejón on 27/01/2017.
 */

public class MarvelService extends Service<Comic> {

    private final static String TAG = MarvelService.class.getName();

    private final static int REQUEST_LIMIT = 20;

    private MarvelApi marvelApi;

    private int offset;

    @Inject
    public MarvelService(Context appContext, MarvelApi marvelApi, MD5 md5, NetworkStateHelper networkStateHelper, @Named(Constants.NAMED_PUBLIC_KEY) String publicKey, @Named(Constants.NAMED_PRIVATE_KEY) String privateKey) {
        super(appContext, md5, networkStateHelper, publicKey, privateKey);
        this.marvelApi = marvelApi;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    protected void executeService(long timeStamp, String hash, RequestConsumer<Comic> callback) {
        marvelApi.getComicsFromCharacter(1009220, timeStamp, getPublicKey(), hash, REQUEST_LIMIT, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback, throwable -> onError(throwable, callback));
    }
}
