package com.antonicastejon.model.repository.entities.errors;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public class NoInternetException extends ResponseError {

    private final static String NO_INTERNET_ERROR = "No internet connection";
    private final static int ERROR_CODE = -1;

    public NoInternetException() {
        super(NO_INTERNET_ERROR, ERROR_CODE);
    }
}
