package com.antonicastejon.marvelcharacters.net.services;

import com.antonicastejon.marvelcharacters.model.Comic;
import com.antonicastejon.marvelcharacters.net.requests.base.RequestConsumer;
import com.antonicastejon.marvelcharacters.utils.crypt.MD5;

import javax.inject.Inject;

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
    public MarvelService(MarvelApi marvelApi, MD5 md5) {
        super(md5);
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
                .subscribe(callback);
    }
}
