package com.antonicastejon.marvelcharacters.net.requests.base;

import com.antonicastejon.marvelcharacters.net.response.ResponseWrapper;

import io.reactivex.functions.Consumer;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class RequestConsumer<T> implements Consumer<ResponseWrapper<T>> {

    private final static int OK_CODE = 200;

    public interface Callback<T> {
        void onResponse(ResponseWrapper.DataContainer<T> data);
        void onError(int code, String message);
    }

    private final Callback<T> callback;

    public RequestConsumer(Callback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void accept(ResponseWrapper<T> responseWrapper) throws Exception {
        int code = responseWrapper.getCode();
        if (code == OK_CODE) {
            callback.onResponse(responseWrapper.getData());
        }
        else {
            onError(code, responseWrapper.getStatus());
        }
    }

    public void onError(int code, String message) {
        callback.onError(code, message);
    }
}
