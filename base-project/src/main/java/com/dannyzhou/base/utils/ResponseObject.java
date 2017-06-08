package com.dannyzhou.base.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by danny on 2017/6/5.
 */
@Getter
@Setter
public class ResponseObject {

    // Result status code.
    // 200 is right.
    private Integer status;
    // Result error message.
    // Often using in status is not 200. for user alert.
    private String errorMessage;
    // Result Object.
    private Object responseObject;

    public ResponseObject(Object responseObject) {
        this.status = 200;
        this.responseObject = responseObject;
    }

    public ResponseObject(Integer status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
