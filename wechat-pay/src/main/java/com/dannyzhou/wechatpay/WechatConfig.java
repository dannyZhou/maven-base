package com.dannyzhou.wechatpay;

import com.dannyzhou.base.utils.PropertiesUtils;

/**
 * Created by danny on 2017/6/9.
 */
public class WechatConfig {

    private static final String PREFIX = "wechat.";

    private static final String APP_ID = PREFIX + "appId";
    private static final String MERCHANT_ID = PREFIX + "merchantId";
    private static final String SOURCE_IP = PREFIX + "sourceIp";
    private static final String PARTNER_ID = PREFIX + "partnerId";
    private static final String PARTNER_KEY = PREFIX + "partnerKey";
    private static final String CERT_LOCATION = PREFIX + "certLocation";

    public static final String NO_CEHCK_USERNAME = "NO_CHECK";

    public static final String ENTERPRISE_PAY_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    public static final String RETURN_CODE = "return_code";
    public static final String RETURN_MESSAGE = "return_msg";
    public static final String RESULT_CODE = "result_code";
    public static final String PAYMENT_NUMBER = "payment_no";
    public static final String RESULT_SUCCESS = "SUCCESS";

    public static String getAppId() {
        return getProperty(APP_ID);
    }

    public static String getMerchantId() {
        return getProperty(MERCHANT_ID);
    }

    public static String getSourceIp() {
        return getProperty(SOURCE_IP);
    }

    public static String getPartnerId() {
        return getProperty(PARTNER_ID);
    }

    public static String getPartnerKey() {
        return getProperty(PARTNER_KEY);
    }

    public static String getCertLocation() {
        return getProperty(CERT_LOCATION);
    }

    private static String getProperty(String key) {
        return PropertiesUtils.getInstance().GetStringByKey(key);
    }
}
