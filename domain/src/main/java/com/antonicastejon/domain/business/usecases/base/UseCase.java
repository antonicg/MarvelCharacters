package com.antonicastejon.domain.business.usecases.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.antonicastejon.domain.RequestConsumer;
import com.antonicastejon.domain.utils.NetworkStateHelper;
import com.antonicastejon.model.repository.entities.errors.NoInternetException;
import com.antonicastejon.model.repository.entities.errors.ResponseError;

import javax.inject.Inject;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public abstract class UseCase<T> {

    private static final int UNKNOWN_ERROR_CODE = -1;

    protected abstract void executeRequest(RequestConsumer<T> callback);

    @Inject
    Context appContext;

    @Inject
    NetworkStateHelper networkStateHelper;

    public void execute(RequestConsumer<T> callback) {
        if (networkStateHelper.isNetworkAvailable(appContext)) {
            executeRequest(callback);
        }
        else {
            onError(new NoInternetException(), callback);
        }
    }

    protected void onError(@NonNull Throwable throwable, RequestConsumer<T> callback) {
        String message = throwable.getMessage();
        if (throwable instanceof ResponseError) {
            ResponseError error = (ResponseError) throwable;
            callback.onError(error.getCode(), message);
        }
        else {
            callback.onError(UNKNOWN_ERROR_CODE, message);
        }
    }
}
