package com.antonicastejon.model.repository.services.base;

import android.util.Log;

import com.antonicastejon.model.repository.api.ResponseWrapper;
import com.antonicastejon.model.repository.crypt.MD5;
import com.antonicastejon.model.repository.entities.Comic;

import io.reactivex.Flowable;

/**
 * Created by Antoni Castejón on 29/03/2017.
 */

public abstract class Service<T> {

    private final static String TAG = Service.class.getName();

    protected abstract Flowable<ResponseWrapper<T>> executeService(long timeStamp, String hash);

    private MD5 md5;
    private long timeStamp;
    private String hash;
    private String publicKey;
    private String privateKey;

    public Service(MD5 md5, String publicKey, String privateKey) {
        this.md5 = md5;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    protected String getPublicKey() {
        return publicKey;
    }


    public void executeRequest() {
        generateNewAuth();
        executeService(timeStamp, hash);
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
        return md5.getMD5(timeStamp + privateKey + publicKey);
    }
}
