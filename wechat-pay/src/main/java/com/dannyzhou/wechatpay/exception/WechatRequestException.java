package com.dannyzhou.wechatpay.exception;

/**
 * Created by danny on 2017/6/9.
 */
public class WechatRequestException extends RuntimeException {
    public WechatRequestException(Exception ex) {
        super(ex);
    }

    public WechatRequestException() {
        super();
    }
}
