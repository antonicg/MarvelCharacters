package com.antonicastejon.domain;

import io.reactivex.functions.Consumer;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class RequestConsumer<T> implements Consumer<T> {

    public interface Callback<T> {
        void onResponse(T data);
        void onError(int code, String message);
    }

    private final Callback<T> callback;

    public RequestConsumer(Callback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void accept(T response) throws Exception {
        callback.onResponse(response);
    }

    public void onError(int code, String message) {
        callback.onError(code, message);
    }
}
