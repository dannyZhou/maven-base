package com.dannyzhou.base.utils;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by danny on 2017/6/5.
 */
@Getter
public class EncryptedResponseObject {

    // Result status code.
    // 200 is right.
    private Integer status;
    // Result error message.
    // Often using in status is not 200. for user alert.
    private String errorMessage;
    // Result Object.
    private Object responseObject;

    public EncryptedResponseObject(Object responseObject) {

        this.status = 200;
        if (!(responseObject instanceof String)) {
            responseObject = new Gson().toJson(responseObject);
        }
        responseObject = EncryptUtils.encrypt(responseObject.toString());
        this.responseObject = responseObject;
    }

    public EncryptedResponseObject(Integer status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
