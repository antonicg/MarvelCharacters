package com.antonicastejon.model.repository.entities.errors;

/**
 * Created by Antoni Castejón on 30/03/2017.
 */

public class ResponseError extends Throwable {

    private int code;

    public ResponseError(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
