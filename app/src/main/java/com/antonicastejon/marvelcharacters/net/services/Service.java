package com.antonicastejon.marvelcharacters.net.services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.antonicastejon.marvelcharacters.net.requests.base.RequestConsumer;
import com.antonicastejon.marvelcharacters.utils.crypt.MD5;
import com.antonicastejon.marvelcharacters.utils.network.NetworkStateHelper;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public abstract class Service<T>  {

    private final static String TAG = MarvelService.class.getName();

    protected abstract void executeService(long timeStamp, String hash, RequestConsumer<T> callback);

    private MD5 md5;
    private long timeStamp;
    private String hash;
    private Context appContext;
    private NetworkStateHelper networkStateHelper;
    private String publicKey;
    private String privateKey;

    public Service(Context appContext, MD5 md5, NetworkStateHelper networkStateHelper, String publicKey, String privateKey) {
        this.md5 = md5;
        this.appContext = appContext;
        this.networkStateHelper = networkStateHelper;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    protected String getPublicKey() {
        return publicKey;
    }

    public void executeRequest(RequestConsumer<T> callback) {
        if (networkStateHelper.isNetworkAvailable(appContext)) {
            generateNewAuth();
            executeService(timeStamp, hash, callback);
        }
        else {
            onError(new NoInternetException(), callback);
        }
    }

    protected void onError(@NonNull Throwable throwable, RequestConsumer<T> callback) {
        String message = throwable.getMessage();
        callback.onError(-1, message);
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

    public static class NoInternetException extends Throwable {

        private final static String NO_INTERNET_ERROR = "No internet connection";

        public NoInternetException() {
            super(NO_INTERNET_ERROR);
        }
    }
}
