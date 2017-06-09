package com.dannyzhou.wechatpay;

/**
 * Created by danny on 2017/6/9.
 */
public interface WechatConfig {

    String APP_ID = "";
    String MERCHANT_NUMBER = "";
    String SOURCE_IP = "";
    String NO_CEHCK_USERNAME = "NO_CHECK";

    String ENTERPRISE_PAY_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    String PARTNER_ID = "";
    String PARTNER_KEY = "";
    String CERT_LOCATION = "";


    String RETURN_CODE = "return_code";
    String RETURN_MESSAGE = "return_msg";
    String RESULT_CODE = "result_code";
    String PAYMENT_NUMBER = "payment_no";
    String RESULT_SUCCESS = "SUCCESS";
}
