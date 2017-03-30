package com.antonicastejon.domain;

import io.reactivex.functions.Consumer;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class RequestConsumer<T> implements Consumer<T> {

    private final static int OK_CODE = 200;

    public interface Callback<T> {
        void onResponse(T data);
        void onError(int code, String message);
    }

    private final Callback<T> callback;

    public RequestConsumer(Callback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void accept(T response) {
        callback.onResponse(response);
    }

    public void onError(int code, String message) {
        callback.onError(code, message);
    }
}
