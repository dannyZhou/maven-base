package com.dannyzhou.base.utils;

import com.google.gson.Gson;

public class EncryptedResponseObject {
    private Integer status;
    private String errorMessage;
    private Object responseObject;

    public Integer getStatus() {
        return this.status;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public Object getResponseObject() {
        return this.responseObject;
    }

    public EncryptedResponseObject(Object responseObject) {
        this.status = Integer.valueOf(200);
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
