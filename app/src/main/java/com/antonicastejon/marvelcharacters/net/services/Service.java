package com.antonicastejon.marvelcharacters.net.services;

import android.util.Log;

import com.antonicastejon.marvelcharacters.net.requests.base.RequestConsumer;
import com.antonicastejon.marvelcharacters.utils.crypt.MD5;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public abstract class Service<T>  {

    private final static String TAG = MarvelService.class.getName();

    private final static String PUBLIC_KEY  = "6a7ed890b4b941a925202a5630d5b162";
    private final static String PRIVATE_KEY = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";

    protected abstract void executeService(long timeStamp, String hash, RequestConsumer<T> callback);

    private MD5 md5;
    private long timeStamp;
    private String hash;

    public Service(MD5 md5) {
        this.md5 = md5;
    }

    public String getPublicKey() {
        return PUBLIC_KEY;
    }

    public void executeRequest(RequestConsumer<T> callback) {
        generateNewAuth();
        executeService(timeStamp, hash, callback);
    }

    private void generateNewAuth() {
        timeStamp = System.currentTimeMillis();
        try {
            hash = getRequestHash(String.valueOf(timeStamp), md5);
        } catch (Exception e) {
            Log.e(TAG, "Error creating the hash for request");
        }
    }

    private String getRequestHash(String timeStamp, MD5 md5) throws Exception {
        return md5.getMD5(timeStamp + PRIVATE_KEY + PUBLIC_KEY);
    }
}