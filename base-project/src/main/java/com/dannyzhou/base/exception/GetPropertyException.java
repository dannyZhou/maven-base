package com.dannyzhou.base.exception;

import java.io.IOException;

/**
 * Created by danny on 2017/6/10.
 */
public class GetPropertyException extends RuntimeException {
    public GetPropertyException() {
        super();
    }

    public GetPropertyException(String error) {
        super(error);
    }
    public GetPropertyException(IOException ex) {
        super(ex);
    }
}
