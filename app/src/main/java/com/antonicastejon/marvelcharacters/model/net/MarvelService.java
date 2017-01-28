package com.antonicastejon.marvelcharacters.model.net;

import android.util.Log;

import com.antonicastejon.marvelcharacters.utils.MD5;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Antoni Castejón on 27/01/2017.
 */

public class MarvelService {

    private final static String TAG = MarvelService.class.getName();

    private final static String PUBLIC_KEY  = "6a7ed890b4b941a925202a5630d5b162";
    private final static String PRIVATE_KEY = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";

    private MarvelApi marvelApi;
    private MD5 md5;

    @Inject
    public MarvelService(MarvelApi marvelApi, MD5 md5) {
        this.marvelApi = marvelApi;
        this.md5 = md5;
    }

    public void getComics() {

        long timeStamp = System.currentTimeMillis();
        String requestHash;
        try {
            requestHash = getRequestHash(String.valueOf(timeStamp), md5);
        } catch (Exception e) {
            Log.e(TAG, "Error creating the hash for request");
            return;
        }
        marvelApi.getComicsFromCharacter(1009220, timeStamp, PUBLIC_KEY, requestHash)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comicDataWrapper -> {
                    Log.d(TAG, "" + comicDataWrapper.getCode() + " " + comicDataWrapper.getStatus());
                });
    }

    private String getRequestHash(String timeStamp, MD5 md5) throws Exception {
        return md5.getMD5(timeStamp + PRIVATE_KEY + PUBLIC_KEY);
    }
}
